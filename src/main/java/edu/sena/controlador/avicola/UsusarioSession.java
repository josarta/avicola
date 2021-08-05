/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controlador.avicola;

import edu.sena.entity.avicola.Usuario;
import edu.sena.facade.avicola.UsuarioFacadeLocal;
import edu.sena.utilidad.avicola.Mail;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Josarta
 */
@Named(value = "usuarioSession")
@SessionScoped
public class UsusarioSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private Usuario usuLogin = new Usuario();
    private Usuario usuReg = new Usuario();
    private String usuCorreo = "";
    private String usuClave = "";

    /**
     * Creates a new instance of UsusarioSession
     */
    public UsusarioSession() {
    }

    public void iniciarSesion() {
        try {
            usuLogin = usuarioFacadeLocal.usuarioLogin(usuCorreo, usuClave);
            if (usuLogin != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("usuario/index.xhtml");
            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'Usuario no encontrado',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Intentar de nuevo'"
                        + "})");
            }
        } catch (Exception e) {
        }
    }

    public void cerrarSesion() throws IOException {
        usuLogin = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        fc.getExternalContext().redirect("../index.xhtml");
    }

    public void validarUsusarioActivo() throws IOException {
        if (usuLogin == null || usuLogin.getUsuCorreoelectronico() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().invalidateSession();
            fc.getExternalContext().redirect("../index.xhtml");
        }
    }

    public void registrarUsuario() {
        try {
            Usuario usuExis = usuarioFacadeLocal.recuperarClave(usuReg.getUsuCorreoelectronico());
            if (usuExis == null || usuExis.getUsuCorreoelectronico() == null) {
                if (usuarioFacadeLocal.registroUsusario(usuReg)) {
                    usuReg = new Usuario();
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'OK!',"
                            + "  text: 'Usuario registrado con exito !!!,"
                            + "  icon: 'success',"
                            + "  confirmButtonText: 'Aceptar'"
                            + "})");
                } else {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'Usuario no registrado',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Intentar mas tarde'"
                            + "})");
                }
            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'El Usuario ya esta registrado',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Recuperar su clave'"
                        + "})");
            }

            usuReg.setUsuEstado(Short.parseShort("1"));

        } catch (Exception e) {
        }
    }

    public void recuperarClave() {
        usuReg = null;
        usuReg = usuarioFacadeLocal.recuperarClave(usuCorreo);
        
        double aleatorio = 100000 * Math.random();
        usuReg.setUsuClave("NC-"+(int)aleatorio+"*@");
        usuarioFacadeLocal.edit(usuReg);
                
        if (usuReg != null) {
            try {
                Mail.recuperarClaves(usuReg.getUsuNombres() + " " +usuReg.getUsuApellidos() , usuCorreo, usuReg.getUsuClave());
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Correo enviado!',"
                        + "  text: 'Port favor verifique su bandeja de entrada',"
                        + "  icon: 'success',"
                        + "  confirmButtonText: 'Ok'"
                        + "})");
            } catch (Exception e) {
                 PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
                
            }

        } else {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'Usuario no encontrado',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Valide su correo Electronico'"
                    + "})");
        }

    }

    public Usuario getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuario usuLogin) {
        this.usuLogin = usuLogin;
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

}
