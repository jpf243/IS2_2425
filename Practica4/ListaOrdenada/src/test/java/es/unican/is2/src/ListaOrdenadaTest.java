package es.unican.is2.src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import es.unican.is2.src.ListaOrdenada;
import java.util.NoSuchElementException;

public class ListaOrdenadaTest {
    private ListaOrdenada<Integer> lista;

    @Before
    public void setUp() {
        lista = new ListaOrdenada<>();
    }

    @Test
    public void testGetValido() {
        lista.add(3);
        lista.add(2);
        lista.add(4);
        assertEquals(lista.get(1), lista.get(1));
        assertEquals(lista.get(2), lista.get(2));
        assertEquals(lista.get(0), lista.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidoNegativo() {
        lista.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidoFueraDeRango() {
        lista.add(3);
        lista.get(3);
    }

    @Test
    public void testAddValido() {
        lista.add(4);
        assertEquals((Integer) 4, lista.get(0));
        lista.add(2);
        assertEquals((Integer) 2, lista.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testAddInvalido() {
        lista.add(null);
        lista.get(0);
        assertEquals(1, lista.size());
    }

    @Test
    public void testRemoveValido() {
        lista.add(3);
        lista.add(2);
        lista.add(4);
        assertEquals((lista.get(1)), lista.remove(1));
        assertEquals(2, lista.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalido() {
        lista.remove(-1);
    }

    @Test
    public void testSize() {
        assertEquals(0, lista.size());
        lista.add(3);
        assertEquals(1, lista.size());
        lista.add(2);
        lista.add(4);
        assertEquals(3, lista.size());
    }

    @Test
    public void testClear() {
        lista.add(3);
        lista.add(2);
        lista.add(4);
        assertEquals(3, lista.size());
        lista.clear();
        assertEquals(2, lista.size());
    }
}
