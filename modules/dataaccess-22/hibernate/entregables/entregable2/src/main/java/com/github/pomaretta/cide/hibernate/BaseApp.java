package com.github.pomaretta.cide.hibernate;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Locale;

import com.github.pomaretta.cide.hibernate.dto.Libros;
import com.github.pomaretta.cide.hibernate.dto.Prestamos;
import com.github.pomaretta.cide.hibernate.dto.Socios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class BaseApp {

    private SessionFactory sessionFactory;

    public BaseApp() {
        this.sessionFactory = buildSessionFactory();
    }

    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(BaseApp.class.getResource("hibernate.cfg.xml"));

        ServiceRegistry serviceRegistry = configuration
                .getStandardServiceRegistryBuilder()
                .applySettings(
                        configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    // Libros
    public void addLibro(Libros libro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(libro);
        session.getTransaction().commit();
        session.close();
    }

    public void removeLibro(Libros libro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(libro);
        session.getTransaction().commit();
        session.close();
    }

    public void updateLibro(Libros libro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(libro);
        session.getTransaction().commit();
        session.close();
    }

    public Libros getLibroByTitulo(String titulo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Libros libro = (Libros) session.createQuery("from Libros where titulo = :titulo")
                .setParameter("titulo", titulo)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return libro;
    }

    // Socios
    public void addSocio(Socios socio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(socio);
        session.getTransaction().commit();
        session.close();
    }

    public void removeSocio(Socios socio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(socio);
        session.getTransaction().commit();
        session.close();
    }

    public void updateSocio(Socios socio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(socio);
        session.getTransaction().commit();
        session.close();
    }

    public Socios getSocioByNombre(String nombre) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Socios socio = (Socios) session.createQuery("from Socios where nombre = :nombre")
                .setParameter("nombre", nombre)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return socio;
    }

    public Socios getSocioByApellidos(String apellidos) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Socios socio = (Socios) session.createQuery("from Socios where apellidos = :apellidos")
                .setParameter("apellidos", apellidos)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return socio;
    }

    // Prestamos
    public void addPrestamo(Prestamos prestamo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(prestamo);
        session.getTransaction().commit();
        session.close();
    }

    public Prestamos[] getPrestamosBySocio(Socios socio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Prestamos[] prestamos = (Prestamos[]) session.createQuery("from Prestamos where id_socio = :id_socio")
                .setParameter("id_socio", socio.getIdSocio())
                .list().toArray(new Prestamos[0]);
        session.getTransaction().commit();
        session.close();
        return prestamos;
    }

    public Libros[] getLibrosInPrestamos() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Libros[] libros = (Libros[]) session.createQuery("from Libros where idLibro in (select idLibro from Prestamos where fin >= DATE(:fin))")
                .setParameter("fin", Date.valueOf(java.time.LocalDate.now(ZoneId.systemDefault())))
                .list().toArray(new Libros[0]);
        session.getTransaction().commit();
        session.close();
        return libros;
    }

    public Libros[] getLibrosFueraDeTiempo() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Libros[] libros = (Libros[]) session.createQuery("from Libros where idLibro in (select idLibro from Prestamos where fin < DATE(:fin))")
                .setParameter("fin", Date.valueOf(java.time.LocalDate.now(ZoneId.systemDefault())))
                .list().toArray(new Libros[0]);
        session.getTransaction().commit();
        session.close();
        return libros;
    }

    public Socios[] sociosConLibrosFueraDeTiempo() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Socios[] socios = (Socios[]) session.createQuery("from Socios where idSocio in (select idSocio from Prestamos where fin < DATE(:fin))")
                .setParameter("fin", Date.valueOf(java.time.LocalDate.now(ZoneId.systemDefault())))
                .list().toArray(new Socios[0]);
        session.getTransaction().commit();
        session.close();
        return socios;
    }

    public static void main(String[] args) throws Exception {
        
        BaseApp app = new BaseApp();

        Libros libro = new Libros();
        libro.setTitulo("El señor de los anillos");
        libro.setEditorial("J.R.R. Tolkien");
        libro.setEjemplares(5);
        libro.setEdicion(1954);
        libro.setPaginas(1234);
        app.addLibro(libro);

        libro = new Libros();
        libro.setTitulo("Percy Jackson");
        libro.setEditorial("Harper Lee");
        libro.setEjemplares(5);
        libro.setEdicion(1954);
        libro.setPaginas(1234);
        app.addLibro(libro);

        libro = new Libros();
        libro.setTitulo("Los tres mosqueteros");
        libro.setEditorial("J.R.R. Tolkien");
        libro.setEjemplares(5);
        libro.setEdicion(1954);
        libro.setPaginas(1234);
        app.addLibro(libro);

        libro = app.getLibroByTitulo("El señor de los anillos");
        System.out.println(
            String.format(
                Locale.ROOT,
                "Libro añadido %s",
                libro.toString()
            )
        );

        Socios socio = new Socios();
        socio.setNombre("J.R.R. Tolkien");
        socio.setApellidos("Tolkien");
        socio.setDireccion("Calle de los señores de los anillos");
        socio.setTelefono("912341234");
        socio.setEdad(100);
        app.addSocio(socio);

        socio = app.getSocioByNombre("J.R.R. Tolkien");
        System.out.println(
            String.format(
                Locale.ROOT,
                "Socio añadido %s",
                socio.toString()
            )
        );

        // // Create Prestamo
        Prestamos prestamo = new Prestamos();
        prestamo.setIdSocio(socio.getIdSocio());
        prestamo.setIdLibro(libro.getIdLibro());
        prestamo.setInicio(new java.sql.Date(0));
        prestamo.setFin(
            new java.sql.Date(
                new java.util.Date().getTime() + (1000 * 60 * 60 * 24 * 365)
            )
        );
        app.addPrestamo(prestamo);

        prestamo = new Prestamos();
        prestamo.setIdSocio(1);
        prestamo.setIdLibro(2);
        prestamo.setInicio(new java.sql.Date(0));
        prestamo.setFin(
            new java.sql.Date(
                new java.util.Date().getTime() - (1000 * 60 * 60 * 24 * 365)
            )
        );
        app.addPrestamo(prestamo);

        Socios s = new Socios();
        s.setIdSocio(1);
        
        Prestamos[] prestamos = app.getPrestamosBySocio(s);

        System.out.println("Prestamos actuales del socio: 1");

        for(Prestamos p : prestamos) {
            System.out.println(
                String.format(
                    Locale.ROOT,
                    "%s",
                    p.toString()
                )
            );
        }

        Libros[] librosPrestados = app.getLibrosInPrestamos();

        System.out.println("Libros prestados actuales");

        for (Libros l : librosPrestados) {
            System.out.println(
                String.format(
                    Locale.ROOT,
                    "%s",
                    l.toString()
                )
            );
        }

        // Libros que han superado la fecha de fin de préstamo.
        Libros[] librosExpirados = app.getLibrosFueraDeTiempo();

        System.out.println("Libros que han superado la fecha de fin de préstamo");

        for (Libros l : librosExpirados) {
            System.out.println(
                String.format(
                    Locale.ROOT,
                    "%s",
                    l.toString()
                )
            );
        }

        // Socios que tienen libros que han superado la fecha de fin de préstamo.
        Socios[] sociosExpirados = app.sociosConLibrosFueraDeTiempo();

        System.out.println("Socios que tienen libros que han superado la fecha de fin de préstamo");

        for (Socios so : sociosExpirados) {
            System.out.println(
                String.format(
                    Locale.ROOT,
                    "%s",
                    so.toString()
                )
            );
        }

    }
}
