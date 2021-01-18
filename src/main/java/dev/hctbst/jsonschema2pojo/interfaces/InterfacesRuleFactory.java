package dev.hctbst.jsonschema2pojo.interfaces;

import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import dev.hctbst.jsonschema2pojo.interfaces.rules.InterfacesObjectRule;
import org.jsonschema2pojo.rules.Rule;
import org.jsonschema2pojo.rules.RuleFactory;
import org.jsonschema2pojo.util.ParcelableHelper;

/**
 * The entry point of the Interfaces jsonschema2pojo extension.
 * <p>
 * Allows the use of additional {@link Rule}s applied to handle interfaces generation related elements.
 *
 * @author Hector Basset
 */
public class InterfacesRuleFactory extends RuleFactory {

	private final ParcelableHelper parcelableHelper = new ParcelableHelper();

	public ParcelableHelper getParcelableHelper() {
		return parcelableHelper;
	}

	@Override
	public Rule<JPackage, JType> getObjectRule() {
		return new InterfacesObjectRule(this);
	}
}
