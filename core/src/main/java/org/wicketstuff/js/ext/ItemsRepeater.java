 /**
 * 
 */
package org.wicketstuff.js.ext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.util.collections.ReadOnlyIterator;

final class ItemsRepeater<T extends ExtComponent> extends AbstractRepeater {
     private static final long serialVersionUID = 1L;

     static class ExtItem extends WebMarkupContainer {
         private static final long serialVersionUID = 1L;

         private ExtItem(final int iteration) {
             super(Integer.toString(iteration).intern());
             setRenderBodyOnly(true);
         }

         @Override
         public void remove(final Component component) {
             getParent().remove(this);
         }
     }

     private int numItems = 0;

     ItemsRepeater(String id) {
         super(id);
         setRenderBodyOnly(true);
     }

     @Override
     protected Iterator<? extends Component> renderIterator() {
         final int iterations = size();
         return new ReadOnlyIterator<Component>() {
             private int index = 0;

             public boolean hasNext() {
                 return index < iterations;
             }

             public Component next() {
                 return get(Integer.toString(index++));
             }
         };
     }

     @Override
     protected void onPopulate() {
     }

     void add(T ec) {
         if (!"item".equals(ec.getId())) {
             throw new IllegalArgumentException("ExtComponent does not have 'item' as it's wicket id");
         }
         // Create item for loop iteration
         ExtItem item = new ExtItem(numItems++);
         add(item);
         item.add(ec);
     }

     Iterator<T> extIterator() {
         return getExtComponents().iterator();
     }

     public final List<T> getExtComponents() {
         final List<T> itemsList = new ArrayList<T>();
         visitChildren(ExtComponent.class, new IVisitor<T>() {

             @Override
             public Object component(ExtComponent component) {
                 itemsList.add((T) component);
                 return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
             }

         });
         return itemsList;
     }

 }