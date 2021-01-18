package dev.hctbst.jsonschema2pojo.interfaces.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JMods;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import dev.hctbst.jsonschema2pojo.interfaces.InterfacesRuleFactory;
import org.jsonschema2pojo.Schema;
import org.jsonschema2pojo.rules.ObjectRule;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Override of the default {@link ObjectRule} to apply interfaces generation related elements.
 *
 * @author Hector Basset
 */
public class InterfacesObjectRule extends ObjectRule {

	public InterfacesObjectRule(InterfacesRuleFactory ruleFactory) {
		super(ruleFactory, ruleFactory.getParcelableHelper(), ruleFactory.getReflectionHelper());
	}

	@Override
	public JType apply(String nodeName, JsonNode node, JsonNode parent, JPackage jPackage, Schema schema) {
		JType type = super.apply(nodeName, node, parent, jPackage, schema);

		type = handleInterfaces(type);

		return type;
	}

	protected JType handleInterfaces(JType type) {
		if (type instanceof JDefinedClass) {
			JDefinedClass generatedClass = (JDefinedClass) type;

			setPrivateFieldValue("classType", JDefinedClass.class, generatedClass, ClassType.INTERFACE);

			new ArrayList<>(generatedClass.fields().values()).forEach(generatedClass::removeField);

			generatedClass.methods()
					.forEach(method -> {
						setPrivateFieldValue("body", JMethod.class, method, null);
						setPrivateFieldValue("mods", JMods.class, method.mods(), JMod.NONE);
					});
		}

		return type;
	}

	private <T> void setPrivateFieldValue(String fieldName, Class<? extends T> clazz, T instance, Object value) {
		Field field;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(instance, value);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
