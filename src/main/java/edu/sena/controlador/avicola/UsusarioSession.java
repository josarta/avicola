/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controlador.avicola;

import edu.sena.entity.avicola.Usuario;
import edu.sena.facade.avicola.UsuarioFacadeLocal;
import edu.sena.utilidad.avicola.Mail;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

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
    private Part mifoto;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

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
        usuReg.setUsuClave("NC-" + (int) aleatorio + "*@");
        usuarioFacadeLocal.edit(usuReg);

        if (usuReg != null) {
            try {
                Mail.recuperarClaves(usuReg.getUsuNombres() + " " + usuReg.getUsuApellidos(), usuCorreo, usuReg.getUsuClave());
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

    public void actualizarMisDatos() {
        try {
            usuarioFacadeLocal.edit(usuLogin);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Usuario Actualizado !',"
                    + "  text: 'Con Exito !!!',"
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

    }

    public void actualizarMifoto() {
        try {

            if (mifoto != null) {
                if (mifoto.getSize() > 900000) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                } else if (mifoto.getContentType().equalsIgnoreCase("image/jpeg") || mifoto.getContentType().equalsIgnoreCase("image/png")) {

                    File carpeta = new File("C:/Imgavicola/Fotos/Usuarios");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    try (InputStream is = mifoto.getInputStream()) {
                        Calendar hoy = Calendar.getInstance();
                        String renombrar = sdf.format(hoy.getTime()) + ".";
                        renombrar += FilenameUtils.getExtension(mifoto.getSubmittedFileName());
                        Files.copy(is, (new File(carpeta, renombrar)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        usuLogin.setUsuFoto(renombrar);
                        usuarioFacadeLocal.edit(usuLogin);
                        PrimeFaces.current().executeScript("Swal.fire({"
                                + "  title: 'Imagen de perfil Actualizada !',"
                                + "  text: 'Con Exito !!!',"
                                + "  icon: 'success',"
                                + "  confirmButtonText: 'Ok'"
                                + "})");
                        PrimeFaces.current().executeScript("document.getElementById('formReset').click()");

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
                            + "  text: 'Tipo de archivo no permitido, recuerde la extencion es "
                            + ".jpeg o .png',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                }

            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'No se puede realizar esta peticion',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Por favor intente mas tarde'"
                        + "})");

            }

        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
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

    public Part getMifoto() {
        return mifoto;
    }

    public void setMifoto(Part mifoto) {
        this.mifoto = mifoto;
    }

}
