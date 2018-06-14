package exemplo.jsf;

import java.io.Serializable;
import java.util.Vector;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Vector<String> nameList;
    private String name;

    public HelloBean() {
        nameList = new Vector<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        nameList.add(name);
    }

    public void setAllNames() {
    }

    public String getAllNames() {
        String str = " | ";
        for (String s : nameList) {
            str = str +  s +" | ";
        }
        return str.trim();
    }

    
    /**
     * Método simples para realizar uma ação sempre que 
     * acontecer uma mudança do valor num certo componente...
     * Este não é o modo convencional. Serve para mostrar que pode ter todo o controlo.
     * 
     * @param comp 
     */
    public void debug1(UIComponent comp) {
        System.out.println("# EVENTO sobre o componente "+comp.getId()+"["+comp.getFamily()+"]");
    }
    
}
