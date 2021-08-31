/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controlador.avicola;

import com.opencsv.CSVReader;
import edu.sena.entity.avicola.Galpon;
import edu.sena.facade.avicola.GalponFacadeLocal;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Josarta
 */
@Named(value = "galponView")
@ViewScoped
public class GalponView implements Serializable {

    @EJB
    GalponFacadeLocal galponFacadeLocal;
    private Galpon newGalpon = new Galpon();
    private Galpon temGalpon = new Galpon();
    private Part archivoCsv;

    /**
     * Creates a new instance of GalponView
     */
    public GalponView() {
    }

    public void cargaInicial() {
        try {

            if (archivoCsv != null) {
                if (archivoCsv.getSize() > 900000) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                } else if (archivoCsv.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {

                    File carpeta = new File("C:/Imgavicola/Archivos/Administrador");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    try (InputStream is = archivoCsv.getInputStream()) {
                        Calendar hoy = Calendar.getInstance();
                        Files.copy(is, (new File(carpeta, archivoCsv.getSubmittedFileName())).toPath(), StandardCopyOption.REPLACE_EXISTING);

                        try (CSVReader reader = new CSVReader(new FileReader("C:/Imgavicola/Archivos/Administrador/" + archivoCsv.getSubmittedFileName()))) {
                            List<String[]> r = reader.readAll();
                            for (String[] st : r) {
                                Galpon objg = galponFacadeLocal.validarExistencia(st[1]);
                                if (objg != null) {
                                    objg.setGalCantidad(Integer.parseInt(st[0]));
                                    galponFacadeLocal.edit(objg);
                                } else {
                                    Galpon newG = new Galpon();
                                    newG.setGalCantidad(Integer.parseInt(st[0]));
                                    newG.setGalUbicacion(st[1]);
                                    galponFacadeLocal.create(newG);
                                }
                            }
                        }
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
                            + ".csv',"
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

        PrimeFaces.current().executeScript("document.getElementById('formReset').click()");
    }

    public List<Galpon> leerTodos() {
        return galponFacadeLocal.todosGalpones();

    }

    public void crearGalpon() {
        try {
            galponFacadeLocal.registroGalpon(newGalpon);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Galpon !',"
                    + "  text: 'Creado satisfactoriamente !!!'"
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

    public void eliminarGalpon(Galpon gpn) {
        try {
            galponFacadeLocal.remove(gpn);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Galpon !',"
                    + "  text: 'Removida satisfactoriamente !!!'"
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

    public void cargaTemporal(Galpon gpt) {
        temGalpon = gpt;
    }

    public void editarGalpon() {
        try {
            galponFacadeLocal.edit(temGalpon);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Galpon !',"
                    + "  text: 'Actualizado satisfactoriamente !!!'"
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

    public Galpon getNewGalpon() {
        return newGalpon;
    }

    public void setNewGalpon(Galpon newGalpon) {
        this.newGalpon = newGalpon;
    }

    public Galpon getTemGalpon() {
        return temGalpon;
    }

    public void setTemGalpon(Galpon temGalpon) {
        this.temGalpon = temGalpon;
    }

    public Part getArchivoCsv() {
        return archivoCsv;
    }

    public void setArchivoCsv(Part archivoCsv) {
        this.archivoCsv = archivoCsv;
    }

}
