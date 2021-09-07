/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controlador.avicola;

import com.opencsv.CSVReader;
import edu.sena.entity.avicola.Cubeta;
import edu.sena.entity.avicola.Foto;
import edu.sena.facade.avicola.CubetaFacadeLocal;
import edu.sena.facade.avicola.FotoFacadeLocal;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author Josarta
 */
@Named(value = "cubetasView")
@ViewScoped
public class CubetasView implements Serializable {

    @EJB
    CubetaFacadeLocal cubetaFacadeLocal;
    @EJB
    FotoFacadeLocal fotoFacadeLocal;
    @Resource(lookup = "java:app/dbs_avicola")
    DataSource dataSource;

    private Cubeta cub_nueva = new Cubeta();
    private Cubeta cub_temporal = new Cubeta();
    private Part cargafoto;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private Part archivoCsv;

    /**
     * Creates a new instance of CubetasView
     */
    public CubetasView() {
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
                                Cubeta objc = cubetaFacadeLocal.validarExistencia(st[0]);
                                if (objc != null) {
                                    //tipo st[0]
                                    //descripcion st[1]
                                    //valor st[2]
                                    //catidad st[3]
                                    objc.setCubDescripcion(st[1]);
                                    objc.setCubValor(Double.parseDouble(st[2]));
                                    objc.setCunCantidad(Integer.parseInt(st[3]));
                                    cubetaFacadeLocal.edit(objc);
                                } else {
                                    Cubeta newC = new Cubeta();
                                    newC.setCubTipo(st[0]);
                                    newC.setCubDescripcion(st[1]);
                                    newC.setCubValor(Double.parseDouble(st[2]));
                                    newC.setCunCantidad(Integer.parseInt(st[3]));
                                    cubetaFacadeLocal.create(newC);
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

    public void descargaReporte() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try {
            File jasper = new File(context.getRealPath("/reportes/cubetas.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Cubetas.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (IOException | SQLException | JRException e) {
            System.out.println("CubetasView.descargaReporte() " + e.getMessage());

        }
    }

    public List<Cubeta> leerTodas() {
        return cubetaFacadeLocal.todasCubetas();
    }

    public List<Cubeta> leerTodasTemporal() {
        List<Cubeta> listacbt = cubetaFacadeLocal.todasCubetas();
        return listacbt;
    }

    public void crearCubeta() {
        try {
            cubetaFacadeLocal.registroCubeta(cub_nueva);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Cubeta !',"
                    + "  text: 'Creada satisfactoriamente !!!'"
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

    public void eliminarCubeta(Cubeta cbt) {
        try {
            cubetaFacadeLocal.remove(cbt);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Cubeta !',"
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

    public void cargaTemporal(Cubeta cbt) {
        cub_temporal = cbt;
    }

    public void editarCubeta() {
        try {
            cubetaFacadeLocal.edit(cub_temporal);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Cubeta !',"
                    + "  text: 'Actualizada satisfactoriamente !!!'"
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

    public void addfoto() {
        try {
            if (cargafoto != null) {
                if (cargafoto.getSize() > 900000) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                } else if (cargafoto.getContentType().equalsIgnoreCase("image/jpeg") || cargafoto.getContentType().equalsIgnoreCase("image/png")) {

                    File carpeta = new File("C:/Imgavicola/Fotos/Cubetas");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    try (InputStream is = cargafoto.getInputStream()) {
                        Calendar hoy = Calendar.getInstance();
                        String renombrar = sdf.format(hoy.getTime()) + ".";
                        renombrar += FilenameUtils.getExtension(cargafoto.getSubmittedFileName());
                        Files.copy(is, (new File(carpeta, renombrar)).toPath(), StandardCopyOption.REPLACE_EXISTING);

                        Foto nuevaFoto = new Foto();
                        nuevaFoto.setFotRuta(renombrar);
                        nuevaFoto.setFotDescripcion(cargafoto.getSubmittedFileName());
                        fotoFacadeLocal.create(nuevaFoto);
                        cubetaFacadeLocal.cargaFotoCubeta(nuevaFoto.getFotFotoid(), cub_temporal.getCubCubetaid());
                        cub_temporal = cubetaFacadeLocal.buscarCubetaId(cub_temporal.getCubCubetaid());

                        PrimeFaces.current().executeScript("Swal.fire({"
                                + "  title: 'Foto cargada !',"
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

    public void removerFoto(Foto fotoIn) {
        try {
            cubetaFacadeLocal.removerFotoCubeta(fotoIn.getFotFotoid(), cub_temporal.getCubCubetaid());
            fotoFacadeLocal.remove(fotoIn);
            cub_temporal = cubetaFacadeLocal.buscarCubetaId(cub_temporal.getCubCubetaid());

            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Foto removida !',"
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

    public Cubeta getCub_nueva() {
        return cub_nueva;
    }

    public void setCub_nueva(Cubeta cub_nueva) {
        this.cub_nueva = cub_nueva;
    }

    public Cubeta getCub_temporal() {
        return cub_temporal;
    }

    public void setCub_temporal(Cubeta cub_temporal) {
        this.cub_temporal = cub_temporal;
    }

    public Part getCargafoto() {
        return cargafoto;
    }

    public void setCargafoto(Part cargafoto) {
        this.cargafoto = cargafoto;
    }

    public Part getArchivoCsv() {
        return archivoCsv;
    }

    public void setArchivoCsv(Part archivoCsv) {
        this.archivoCsv = archivoCsv;
    }

}
