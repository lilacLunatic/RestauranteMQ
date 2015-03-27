/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;

/**
 *
 * @author Aluno
 * @param <T>
 * @param <PK>
 */
public interface Dao<T, PK> {

    void save(T entity);

    void delete(PK id);

    List<T> listAll();
}
