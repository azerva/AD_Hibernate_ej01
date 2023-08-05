/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entidades.Empleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author ROZER
 */
public class ConsultasController implements ActionListener {

    private final ConsultasView view;
    private Session session;

    /**
     * Inicializamos las consultas HQL
     */
    private static String Consulta1HQL = "from Empleados where salario < 1600";
    private static String Consulta2HQL = "from Empleados order by salario asc";
    private static String Consulta3HQL = "from Empleados where departamentos.loc != 'granada'";
    private static String Consulta4HQL = "select emp.apellido, emp.salario, emp.departamentos.deptNo from Empleados as emp where emp.salario > (select max(emp.salario) from Empleados as emp where emp.departamentos.deptNo = '10')";
    private static String Consulta5HQL = "select emp.apellido, emp.salario, emp.departamentos.deptNo from Empleados as emp where emp.salario < (select min(em.salario) from Empleados as em where em.departamentos.deptNo='20')";
    private static String Consulta6HQL = "select emp.departamentos.dnombre, count(emp) from Empleados as emp group by emp.departamentos.dnombre having count(emp)>1";

    ConsultasController(ConsultasView view) {

	this.view = view;

	events();
    }

    /**
     * Creamos método general para crear una sesión nueva con hibernate
     */
    private void newSession() {
	session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
    }

    /**
     * Creamos método general para cerrar una sesión con hibernate
     */
    private void closeSession() {
	session.getTransaction().commit();
	session.close();
    }

    private void events() {
	view.btn_consulta1.addActionListener(this);
	view.btn_consulta2.addActionListener(this);
	view.btn_consulta3.addActionListener(this);
	view.btn_consulta4.addActionListener(this);
	view.btn_consulta5.addActionListener(this);
	view.btn_consulta6.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

	Object ae = e.getSource();

	/**
	 * Creamos las acciones que va a generar cada boton de la aplicación
	 */
	if (ae == view.btn_consulta1) {
	    try {
		newSession();
		Query hql = session.createQuery(Consulta1HQL);
		List<Empleados> resultList = hql.list();
		consulta1(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }
	}

	if (ae == view.btn_consulta2) {
	    try {
		newSession();
		Query consulta = session.createQuery(Consulta2HQL);
		List resultList = consulta.list();
		consulta2(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }

	}

	if (ae == view.btn_consulta3) {
	    try {
		newSession();
		Query consulta = session.createQuery(Consulta3HQL);
		List resultList = consulta.list();
		consulta3(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }

	}

	if (ae == view.btn_consulta4) {
	    try {
		newSession();
		Query consulta = session.createQuery(Consulta4HQL);
		List resultList = consulta.list();
		consulta4(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }

	}

	if (ae == view.btn_consulta5) {
	    try {
		newSession();
		Query consulta = session.createQuery(Consulta5HQL);
		List resultList = consulta.list();
		consulta5(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }

	}

	if (ae == view.btn_consulta6) {
	    try {
		newSession();
		Query consulta = session.createQuery(Consulta6HQL);
		List resultList = consulta.list();
		consulta6(resultList);
		closeSession();

	    } catch (HibernateException hex) {

		hex.printStackTrace();
	    }

	}
    }

    /**
     * Creamos los métodos que van a realizar las consultas y que agregaremos en
     * el actionPerformer
     *
     * @param resultList devuelve el resultado en forma de lista
     */
    private void consulta1(List resultList) {

	Vector<String> tableHeaders = new Vector<String>();
	Vector tableData = new Vector();
	tableHeaders.add("Apellido");
	tableHeaders.add("Salario");
	tableHeaders.add("Nº Empleado");

	for (Object object : resultList) {

	    Empleados emp = (Empleados) object;
	    Vector<Object> oneRow = new Vector<Object>();
	    oneRow.add(emp.getApellido());
	    oneRow.add(emp.getSalario());
	    oneRow.add(emp.getEmpNo());
	    tableData.add(oneRow);

	}
	view.tabConsultas.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    private void consulta2(List resultList) {

	Vector<String> tableHeaders = new Vector<>();
	Vector tableData = new Vector();
	tableHeaders.add("Nº Empleado");
	tableHeaders.add("Nº Dept");
	tableHeaders.add("Salario");

	for (Object object : resultList) {

	    Empleados emp = (Empleados) object;
	    Vector<Object> oneRow = new Vector<Object>();
	    oneRow.add(emp.getEmpNo());
	    oneRow.add(emp.getDepartamentos().getDeptNo());
	    oneRow.add(emp.getSalario());

	    tableData.add(oneRow);

	}
	view.tabConsultas.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    private void consulta3(List resultList) {

	Vector<String> tableHeaders = new Vector<String>();
	Vector tableData = new Vector();
	tableHeaders.add("Nº Empleado");
	tableHeaders.add("Apellido");
	tableHeaders.add("Oficio");
	tableHeaders.add("Dir");
	tableHeaders.add("Fecha Alta");
	tableHeaders.add("Salario");
	tableHeaders.add("Comisión");
	tableHeaders.add("Departamento");

	for (Object object : resultList) {

	    Empleados emp = (Empleados) object;
	    Vector<Object> oneRow = new Vector<Object>();
	    oneRow.add(emp.getEmpNo());
	    oneRow.add(emp.getApellido());
	    oneRow.add(emp.getOficio());
	    oneRow.add(emp.getDir());
	    oneRow.add(emp.getFechaAlt());
	    oneRow.add(emp.getSalario());
	    oneRow.add(emp.getComision());
	    oneRow.add(emp.getDepartamentos().getDeptNo());

	    tableData.add(oneRow);

	}
	view.tabConsultas.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    private void consulta4(List resultList) {

	DefaultTableModel model = new DefaultTableModel();
	//Definimos las columnas de la tabla.
	model.addColumn("Apellido");
	model.addColumn("Salario");
	model.addColumn("Departamento");

	//Al ser una lista de arrays el for se declara de este modo
	for (int i = 0; i < resultList.size(); i++) {
	    model.addRow((Object[]) resultList.get(i));
	}
	view.tabConsultas.setModel(model);
    }

    private void consulta5(List resultList) {

	DefaultTableModel model = new DefaultTableModel();
	//Definimos las columnas de la tabla.
	model.addColumn("Apellido");
	model.addColumn("Salario");
	model.addColumn("Departamento");
	//Al ser una lista de arrays el for se declara de este modo
	for (int i = 0; i < resultList.size(); i++) {
	    model.addRow((Object[]) resultList.get(i));
	}
	view.tabConsultas.setModel(model);
    }

    private void consulta6(List resultList) {

	DefaultTableModel model = new DefaultTableModel();
	model.addColumn("Departamento");
	model.addColumn("Total de empleados");

	for (int i = 0; i < resultList.size(); i++) {
	    model.addRow((Object[]) resultList.get(i));
	}
	view.tabConsultas.setModel(model);
    }
}
