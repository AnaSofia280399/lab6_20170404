package com.example.lab6_sol.repository;

import com.example.lab6_sol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByRolid(int rol);

    @Query(value = "select * from usuario where usuario.rolid = 4", nativeQuery = true)
    List<Usuario> obtenerEstudiantes(int idrol);
}
