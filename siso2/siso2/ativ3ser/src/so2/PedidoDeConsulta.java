/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2;

/**
 *
 * @author ir
 */
public class PedidoDeConsulta extends Pedido implements java.io.Serializable{
 
    private String matricula;
 
    public PedidoDeConsulta(String matricula) {
        this.matricula= matricula;
    }
 
    public String getMatricula() {
        return matricula;
    }
}
