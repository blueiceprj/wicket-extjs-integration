package org.wicketstuff.js.ext.form;

import org.apache.wicket.model.IModel;
import org.wicketstuff.js.ext.util.ExtClass;
import org.wicketstuff.js.ext.util.ExtProperty;

@ExtClass("Ext.form.TimeField")
public class ExtTimeField<T> extends ExtComboBox<T> {
	
	@ExtProperty
	protected String minValue;
	@ExtProperty
	protected String maxValue;
	
	public ExtTimeField(String id, IModel<T> model) {
		super(id, model);
	}

	public ExtTimeField(String id) {
		super(id);
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

}
