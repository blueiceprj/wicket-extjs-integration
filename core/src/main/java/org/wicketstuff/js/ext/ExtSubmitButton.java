package org.wicketstuff.js.ext;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.wicketstuff.js.ext.util.JSONIdentifier;

public class ExtSubmitButton extends AbstractExtButton {

    public ExtSubmitButton() {
        this("item", null);
    }

    public ExtSubmitButton(String id) {
        this(id, null);
    }

    public ExtSubmitButton(final IModel<String> save) {
        this("item", save);
    }

    public ExtSubmitButton(String id, IModel<String> text) {
        super(id, text);
    }

    @Override
    protected void onRenderProperties(JSONObject properties) throws JSONException {
        Form<?> form = findParent(Form.class);
        ExtAjaxFormSubmitBehaviour submitBehavior = new ExtAjaxFormSubmitBehaviour(form, "handler");
        add(submitBehavior);
        submitBehavior.addListener(properties);
        super.onRenderProperties(properties);
    }

    private final class ExtAjaxFormSubmitBehaviour extends AjaxFormSubmitBehavior {

        public ExtAjaxFormSubmitBehaviour(Form<?> form, String event) {
            super(form, event);
        }

        private void addListener(JSONObject properties) {
            try {
                properties.put(getEvent(), new JSONIdentifier("function() {" + getEventHandler() + ";}"));
            } catch (JSONException e) {
                throw new WicketRuntimeException(e);
            }
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target) {

        }

        @Override
        protected void onError(AjaxRequestTarget target) {

        }
    }

}
