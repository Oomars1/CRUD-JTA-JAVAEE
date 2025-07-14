/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Usuario
 */
@Named("personaBean")
@ViewScoped
public class PersonaBean implements Serializable {

    @EJB
    private PersonaFacade personaFacade;

    private Persona persona = new Persona();
    private List<Persona> listarPersonas;

    @PostConstruct
    public void init() {
        cargarPersonas();
    }

    public void cargarPersonas() {
        listarPersonas = personaFacade.findAll();
    }

    public void crearPersonas() {
        personaFacade.create(persona);
        persona = new Persona();
        cargarPersonas();
    }

    public void eliminarPersona(Persona p) {
        personaFacade.remove(p);
        cargarPersonas();
    }

    public void obtenerPersona(Persona p) {
        this.persona = p;
    }

    public void actualizarPersona() {
        personaFacade.edit(persona);
        persona = new Persona();
        cargarPersonas();
    }

    public void generarReporte() {
        try {
            // Ruta del archivo .jrxml
            String jrxmlPath = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/usuarios.jrxml");

            // Compilar en memoria
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

            // Usar la lista como fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listarPersonas);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Reporte de Usuarios");

            // Llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

            // Exportar a PDF
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=usuarios.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListarPersonas() {
        return listarPersonas;
    }

    public void setListarPersonas(List<Persona> listarPersonas) {
        this.listarPersonas = listarPersonas;
    }

}
