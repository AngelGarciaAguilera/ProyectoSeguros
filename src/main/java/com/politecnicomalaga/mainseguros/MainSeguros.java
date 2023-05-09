/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.politecnicomalaga.mainseguros;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mint
 */
public class MainSeguros {

    private static Oficina miOficina;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int opcion;

        do {
            //mostrar menú
            menuPrincipal();
            opcion = leerIntTeclado(sc);
            //Si se escoge una opcion diferente a generarOficina y la Oficina no existe, avisa de que hay que generarla primero.
            if (opcion > 1 && opcion < 8 && MainSeguros.miOficina == null) {
                System.out.println("La Oficina no ha sido creada.");
            } else {
                switch (opcion) {
                    case 1:
                        generarOficina(sc);
                        break;

                    case 2:
                        altaCliente(sc);
                        break;

                    case 3:
                        eliminarCliente(sc);
                        break;

                    case 4:
                        actualizarCliente(sc);
                        break;

                    case 5:
                        buscarCliente(sc);
                        break;

                    case 6:
                        buscarClienteApellidos(sc);
                        break;

                    case 7:
                        mostrarClientes(sc);
                        break;

                    case 8:
                        saveOficina();
                        break;

                    case 9:
                        loadOficina();
                        break;

                    case 10:
                        saveOficinaJSON();
                        break;

                    case 11:
                        loadOficinaJSON();
                        break;
                    
                    case -1:
                        System.out.println("Introduzca un número como opción.");
                        break;

                    default:
                        continuar = false;
                        sc.close();
                        break;
                }
            }
        } while (continuar);
    }

    private static void menuPrincipal() {
        System.out.println("\n---------------------------------------");
        System.out.println("MENÚ");
        System.out.println("\n1. Crear/modificar Oficina.");
        System.out.println("2. Dar de alta cliente.");
        System.out.println("3. Eliminar cliente.");
        System.out.println("4. Actualizar datos de cliente.");
        System.out.println("5. Buscar cliente.");
        System.out.println("6. Buscar clientes por apellidos.");
        System.out.println("7. Listar clientes.");
        System.out.println("8. Guardar datos Oficina.");
        System.out.println("9. Cargar datos Oficina.");
        System.out.println("Otra opción: SALIR");
        System.out.println("---------------------------------------");
        System.out.println("\nSeleccione una opción:");
    }

    private static void menuBuscar() {
        System.out.println("\n---------------------------------------");
        System.out.println("MENÚ BUSCAR");
        System.out.println("1. Dar de alta incidencia.");
        System.out.println("2. Eliminar incidencia.");
        System.out.println("3. Listar incidencias.");
        System.out.println("4. Pagar incidencia.");
        System.out.println("Otra opción: SALIR");
        System.out.println("---------------------------------------");
        System.out.println("\nSeleccione una opción:");
    }

    // Recogemos un número de teclado, si nos dan algo que no es un número, ponemos
    // -1 para repetir entrada
    private static int leerIntTeclado(Scanner sc) {
        int iOpcion;
        try {
            iOpcion = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
        return iOpcion;
    }

    // Recogemos un número float de teclado, si nos dan algo que no es un número
    // float, ponemos -1f
    private static float leerFloatTeclado(Scanner sc) {
        float fOpcion;
        try {
            fOpcion = sc.nextFloat();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1f;
        }
        return fOpcion;
    }

    // Recoger un string de teclado
    private static String leerStringTeclado(Scanner sc) {

        String sEntrada;
        try {
            sEntrada = sc.nextLine();
        } catch (InputMismatchException e) {
            return "";
        }
        return sEntrada;
    }

    private static void generarOficina(Scanner sc) {
        String codigoOficina, nombre, direccion, ciudad, cp, telefono, email;

        System.out.println("\n---------------------------------------");
        System.out.println("GENERAR OFICINA\n");
        //Pido los datos y los almaceno.
        System.out.println("Código de la Oficina:");
        codigoOficina = leerStringTeclado(sc);
        System.out.println("Nombre de la Oficina:");
        nombre = leerStringTeclado(sc);
        System.out.println("Dirección de la Oficina:");
        direccion = leerStringTeclado(sc);
        System.out.println("Ciudad de la Oficina:");
        ciudad = leerStringTeclado(sc);
        System.out.println("CP de la Oficina:");
        cp = leerStringTeclado(sc);
        System.out.println("Teléfono de la Oficina:");
        telefono = leerStringTeclado(sc);
        System.out.println("Email de la Oficina:");
        email = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        //Si la Oficina no existe, la creo. Si existe, cambio los datos.
        if (miOficina == null) {
            miOficina = new Oficina(codigoOficina, nombre, direccion, ciudad, cp, telefono, email);
            System.out.println("Oficina generada con éxito.");
        } else {
            miOficina.setCodOficina(codigoOficina);
            miOficina.setNombre(nombre);
            miOficina.setDireccion(direccion);
            miOficina.setCiudad(ciudad);
            miOficina.setCp(cp);
            miOficina.setTelefono(telefono);
            miOficina.setEmail(email);
            System.out.println("Los datos de la Oficina se han actualizado con éxito.");
        }
    }

    private static void altaCliente(Scanner sc) {
        String dni, codigoPoliza, nombre, apellidos, direccion, telefono, email;

        System.out.println("\n---------------------------------------");
        System.out.println("ALTA CLIENTE\n");
        //Pido los datos y los almaceno.
        System.out.println("DNI del Cliente:");
        dni = leerStringTeclado(sc);
        System.out.println("Código de póliza del Cliente:");
        codigoPoliza = leerStringTeclado(sc);
        System.out.println("Nombre del Cliente:");
        nombre = leerStringTeclado(sc);
        System.out.println("Apellidos del Cliente:");
        apellidos = leerStringTeclado(sc);
        System.out.println("Dirección del Cliente:");
        direccion = leerStringTeclado(sc);
        System.out.println("Teléfono del Cliente:");
        telefono = leerStringTeclado(sc);
        System.out.println("Email del Cliente:");
        email = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        if (miOficina.addCliente(dni, codigoPoliza, nombre, apellidos, direccion, telefono, email)) {
            System.out.println("Cliente dado de alta con éxito.");
        } else {
            System.out.println("El cliente no se ha podido dar de alta.");
        }
    }

    private static void eliminarCliente(Scanner sc) {
        String dni;

        System.out.println("\n---------------------------------------");
        System.out.println("ELIMINAR CLIENTE\n");
        System.out.println("---------------------------------------");

        System.out.println("DNI del Cliente:");
        dni = leerStringTeclado(sc);

        if (miOficina.deleteCliente(dni)) {
            System.out.println("Cliente eliminado con éxito.");
        } else {
            System.out.println("El cliente no se ha podido eliminar.");
            System.out.println("Comprube que el DNI introducido es correcto o que el Cliente no tiene incidencias pendientes de pago.");
        }
    }

    private static void actualizarCliente(Scanner sc) {
        String dniIn, dni, codigoPoliza, nombre, apellidos, direccion, telefono, email;

        System.out.println("\n---------------------------------------");
        System.out.println("ACTUALIZAR CLIENTE\n");
        //Pido los datos y los almaceno.
        System.out.println("DNI del Cliente:");
        dniIn = leerStringTeclado(sc);
        if (miOficina.buscarCliente(dniIn) != null) {
            //Si existe el cliente con el DNI introducido...
            System.out.println("Introduce los nuevos datos del Cliente");

            System.out.println("\nDNI del Cliente:");
            dni = leerStringTeclado(sc);
            System.out.println("Código de póliza del Cliente:");
            codigoPoliza = leerStringTeclado(sc);
            System.out.println("Nombre del Cliente:");
            nombre = leerStringTeclado(sc);
            System.out.println("Apellidos del Cliente:");
            apellidos = leerStringTeclado(sc);
            System.out.println("Dirección del Cliente:");
            direccion = leerStringTeclado(sc);
            System.out.println("Teléfono del Cliente:");
            telefono = leerStringTeclado(sc);
            System.out.println("Email del Cliente:");
            email = leerStringTeclado(sc);
            System.out.println("---------------------------------------");

            if (miOficina.updateCliente(dniIn, dni, codigoPoliza, nombre, apellidos, direccion, telefono, email)) {
                System.out.println("Cliente actualizado con éxito.");
            } else {
                System.out.println("El cliente no se ha podido actualizar.");
            }
        } else {
            System.out.println("El DNI introducido no pertenece a ningún Cliente.");
        }
    }

    private static void buscarCliente(Scanner sc) {
        String dni;
        int opcionBusqueda;

        System.out.println("\n---------------------------------------");
        System.out.println("BUSCAR CLIENTE\n");
        //Pido los datos y los almaceno.
        System.out.println("DNI del Cliente:");
        dni = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        if (miOficina.buscarCliente(dni) != null) {
            Cliente c = miOficina.buscarCliente(dni);
            System.out.println(c.toString());

            menuBuscar();
            opcionBusqueda = leerIntTeclado(sc);
            switch (opcionBusqueda) {
                case 1:
                    altaIncidencia(sc, c);
                    break;

                case 2:
                    eliminarIncidencia(sc, c);
                    break;

                case 3:
                    mostrarIncidencias(sc, c);
                    break;

                case 4:
                    pagarIncidencia(sc, c);
                    break;

                default:
                    break;
            }
        } else {
            System.out.println("El DNI introducido no pertenece a ningún Cliente.");
        }
    }

    private static void buscarClienteApellidos(Scanner sc) {
        String apellidos, dni;
        int opcionBusqueda;

        System.out.println("\n---------------------------------------");
        System.out.println("BUSCAR CLIENTE APELLIDOS\n");
        //Pido los datos y los almaceno.
        System.out.println("Apellidos del Cliente:");
        apellidos = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        if (miOficina.buscarClientes(apellidos) != null) {
            //Si ha encontrado clientes con esos apellidos...
            Cliente[] c = miOficina.buscarClientes(apellidos);
            for (int i = 0; i < c.length; i++) {
                Cliente cl = c[i];
                System.out.println(cl.toString());
            }

            menuBuscar();
            opcionBusqueda = leerIntTeclado(sc);
            switch (opcionBusqueda) {
                case 1:
                    System.out.println("DNI del Cliente:");
                    dni = leerStringTeclado(sc);
                    if (miOficina.buscarCliente(dni) != null) {
                        Cliente cli = miOficina.buscarCliente(dni);
                        altaIncidencia(sc, cli);
                    } else {
                        System.out.println("El DNI introducido no pertenece a ningún Cliente.");
                    }
                    break;

                case 2:
                    System.out.println("DNI del Cliente:");
                    dni = leerStringTeclado(sc);
                    if (miOficina.buscarCliente(dni) != null) {
                        Cliente cli = miOficina.buscarCliente(dni);
                        eliminarIncidencia(sc, cli);
                    } else {
                        System.out.println("El DNI introducido no pertenece a ningún Cliente.");
                    }
                    break;

                case 3:
                    System.out.println("DNI del Cliente:");
                    dni = leerStringTeclado(sc);
                    if (miOficina.buscarCliente(dni) != null) {
                        Cliente cli = miOficina.buscarCliente(dni);
                        mostrarIncidencias(sc, cli);
                    } else {
                        System.out.println("El DNI introducido no pertenece a ningún Cliente.");
                    }
                    break;

                case 4:
                    System.out.println("DNI del Cliente:");
                    dni = leerStringTeclado(sc);
                    if (miOficina.buscarCliente(dni) != null) {
                        Cliente cli = miOficina.buscarCliente(dni);
                        mostrarIncidencias(sc, cli);
                    } else {
                        System.out.println("El DNI introducido no pertenece a ningún Cliente.");
                    }
                    break;

                default:
                    break;
            }
        } else {
            System.out.println("Los apellidos introducidos no pertenecen a ningún Cliente.");
        }
    }

    private static void mostrarClientes(Scanner sc) {

        System.out.println("\n---------------------------------------");
        System.out.println("MOSTRAR CLIENTES\n");
        System.out.println("---------------------------------------");

        if (miOficina.listarClientes() != null) {
            Cliente[] c = miOficina.listarClientes();
            for (int i = 0; i < c.length; i++) {
                Cliente cl = c[i];
                System.out.println(cl.toString());
            }
        } else {
            System.out.println("Aún no se ha añadido ningún Cliente.");
        }
    }

    private static void altaIncidencia(Scanner sc, Cliente c) {
        String fecha, matriculaPropia, matriculaAjena, descripcion, dniAjeno;
        int hora, maxDias;

        System.out.println("\n---------------------------------------");
        System.out.println("ALTA INCIDENCIA\n");
        //Pido los datos y los almaceno.
        System.out.println("\nFecha de Incidencia:");
        fecha = leerStringTeclado(sc);
        System.out.println("Hora de Incidencia:");
        hora = leerIntTeclado(sc);
        System.out.println("Matrícula propia:");
        matriculaPropia = leerStringTeclado(sc);
        System.out.println("Matrícula ajena:");
        matriculaAjena = leerStringTeclado(sc);
        System.out.println("Descripción de Incidencia:");
        descripcion = leerStringTeclado(sc);
        System.out.println("DNI ajeno de Incidencia: (Rellenar en caso de IncidenciaAjena)");
        dniAjeno = leerStringTeclado(sc);
        System.out.println("Días máximos para resolver la Incidencia: (Rellenar en caso de IncidenciaUrgente. Introducir -1 en caso de otra Incidencia.)");
        maxDias = leerIntTeclado(sc);
        System.out.println("---------------------------------------");

        if (c.addIncidencia(fecha, hora, matriculaPropia, matriculaAjena, descripcion, maxDias, dniAjeno)) {
            System.out.println("Incidencia creada con éxito.");
        } else {
            System.out.println("La incidencia no se ha podido crear.");
        }
    }

    private static void eliminarIncidencia(Scanner sc, Cliente c) {
        String codigoIncidencia;

        System.out.println("\n---------------------------------------");
        System.out.println("ELIMINAR INCIDENCIA\n");
        System.out.println("---------------------------------------");

        if (c.listarIncidencias() != null) {
            System.out.println(c.listarIncidencias());
            System.out.println("Introduce el código de incidencia de la Incidencia a eliminar:");
            codigoIncidencia = leerStringTeclado(sc);
            if (c.deleteIncidencia(codigoIncidencia)) {
                System.out.println("Incidencia eliminada con éxito.");
            } else {
                System.out.println("La incidencia está pendiente de pago.");
            }
        } else {
            System.out.println("Aún no se ha añadido ninguna Incidencia.");
        }
    }

    private static void mostrarIncidencias(Scanner sc, Cliente c) {

        System.out.println("\n---------------------------------------");
        System.out.println("MOSTRAR INCIDENCIAS\n");
        System.out.println("---------------------------------------");

        if (c.listarIncidencias() != null) {
            System.out.println(c.listarIncidencias());
        } else {
            System.out.println("Aún no se ha añadido ninguna Incidencia.");
        }
    }

    private static void pagarIncidencia(Scanner sc, Cliente c) {
        String codigoIncidencia;

        System.out.println("\n---------------------------------------");
        System.out.println("PAGAR INCIDENCIA\n");
        System.out.println("---------------------------------------");

        if (c.listarIncidencias() != null) {
            System.out.println(c.listarIncidencias());
            System.out.println("Introduce el código de incidencia de la Incidencia a pagar:");
            codigoIncidencia = leerStringTeclado(sc);
            if (c.buscarIncidencia(codigoIncidencia) != null) {
                //Si encuentra una incidencia...
                Incidencia i = c.buscarIncidencia(codigoIncidencia);
                //La cobra.
                i.setCobrado();
                System.out.println("Incidencia cobrada con éxito.");
            } else {
                System.out.println("El código incidencia introducino no pertenece a ninguna incidencia.");
            }
        } else {
            System.out.println("Aún no se ha añadido ninguna Incidencia.");
        }
    }

    private static void saveOficina() {
        if (ControladorFicheros.writeText("Oficina.csv", miOficina.toCSV())) {
            System.out.println("Proceso de volcado a disco exitoso");
        } else {
            System.out.println("Error al escribir en disco. ¿Tiene espacio en el disco?");
        }
    }

    private static void loadOficina() {
        String csv;
        csv = ControladorFicheros.readText("Oficina.csv");
        miOficina = new Oficina(csv);
        System.out.println("Fichero cargado con éxito.");
    }

    private static void saveOficinaJSON() {
        try{
            ControladorFicheros.writeJson(miOficina, "Oficina.json");
            System.out.println("Se ha exportado la oficina...");
        }catch(IOException io){
            System.out.println("No se ha encontrado el archivo");
        }
        
    }

    private static void loadOficinaJSON() {
        String json = ControladorFicheros.readText("Oficina.json");
        miOficina = (Oficina) ControladorFicheros.readJson(json, miOficina);
        System.out.println("Se ha importado la oficina...");
    }

}
