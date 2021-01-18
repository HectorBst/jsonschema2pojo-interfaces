package dev.hctbst.jsonschema2pojo.interfaces.rules;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import dev.hctbst.jsonschema2pojo.interfaces.InterfacesRuleFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Hector Basset
 */
@RunWith(JUnitPlatform.class)
class InterfacesObjectRuleTest {

	final JCodeModel owner = new JCodeModel();
	final JDefinedClass clazz = owner._class("test.Test");
	final InterfacesObjectRule interfacesObjectRule = new InterfacesObjectRule(new InterfacesRuleFactory());

	public InterfacesObjectRuleTest() throws JClassAlreadyExistsException {
	}

	@Test
	void when_object_must_be_interface() {

		// Given

		// When
		interfacesObjectRule.handleInterfaces(clazz);

		// Then
		assertThat(clazz.isInterface()).isTrue();
	}

	@Test
	void when_object_methods_must_be_interface() {

		// Given
		clazz.method(JMod.PUBLIC, String.class, "getString");

		// When
		interfacesObjectRule.handleInterfaces(clazz);

		// Then
		JMethod method = clazz.getMethod("getString", new JType[]{});
		assertThat(method.mods().getValue()).isEqualTo(JMod.NONE);
		assertThat(method.body().getContents()).isEmpty();
	}
}
