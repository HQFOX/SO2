package teste;

import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 * Classe baseada no exemplo fornecido em Java7 EE Tutorial
 *
 */
@ManagedBean
@SessionScoped
public class UserNumberBean implements Serializable {

    private static final long serialVersionUID = 5443351151396868724L;
    Integer randomInt = null;
    Integer userNumber = null;
    String response = null;
    private long maximum = 10;
    private long minimum = 0;

    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = new Integer(randomGR.nextInt(10));
        // Print number to server log
        System.out.println("Duke's number: " + randomInt);
    }

    public void setUserNumber(Integer user_number) {
        userNumber = user_number;
    }

    public void getNewNumber() {
        Random randomGR = new Random();
        randomInt = new Integer(randomGR.nextInt(10));
        // Print number to server log
        System.out.println("Duke's NEW number: " + randomInt);
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
            return "Yay! You got it! (ACERTOU)";
        } else {
            return "Sorry, " + userNumber + " is incorrect.";
        }
    }

    public long getMaximum() {
        return (this.maximum);
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public long getMinimum() {
        return (this.minimum);
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public void validateNumberRange(FacesContext context, UIComponent toValidate, Object value) {
        int input = (Integer) value;

        if (input < minimum || input > maximum) {
            ((UIInput) toValidate).setValid(false);

            FacesMessage message = new FacesMessage("O seu palpite é inválido por estar fora dos limites: "
                    + input + " não está entre " + minimum + " e " + maximum);

            context.addMessage(toValidate.getClientId(context), message);
        }
    }

}
