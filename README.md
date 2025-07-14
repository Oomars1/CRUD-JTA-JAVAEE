este es el proyecto base aca adjunto imagen del pool de conexiones en payara 
<img width="1336" height="630" alt="image" src="https://github.com/user-attachments/assets/ee82e127-d635-4cd4-9213-ee1b7999881a" />

<img width="1552" height="442" alt="image" src="https://github.com/user-attachments/assets/4f555e63-f3ab-46f9-be5d-7ca92eecdf8f" />

resources:
<img width="1528" height="540" alt="image" src="https://github.com/user-attachments/assets/ccf7aa6e-1c70-45fb-b2c1-b7e1d40ce42b" />

recuerda aca no estas hacuendo peticiones directas con jpql

ejemplo buscar nombre exacto:
public List<Persona> buscarPorNombre(String nombre) {
    return em.createQuery("SELECT p FROM Persona p WHERE p.nombre = :nombre", Persona.class)
             .setParameter("nombre", nombre)
             .getResultList();
}

buscar por email:
public List<Persona> buscarPorEmailLike(String email) {
    return em.createQuery("SELECT p FROM Persona p WHERE LOWER(p.email) LIKE LOWER(:email)", Persona.class)
             .setParameter("email", "%" + email + "%")
             .getResultList();
}

contar personas:
public long contarPersonas() {
    return em.createQuery("SELECT COUNT(p) FROM Persona p", Long.class)
             .getSingleResult();
}

recuerda eso va en el archivo Fapade
se llaman en el bean como cualquier otro metodo:

