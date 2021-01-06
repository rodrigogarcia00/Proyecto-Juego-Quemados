package ar.edu.unlam.pb2.quemado;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unlam.pb2.quemado.Equipo;
import ar.edu.unlam.pb2.quemado.Quemado;

public class QuemadoTest {

	private static final Integer CANTIDAD_JUGADORES = 10;
	private Quemado juego = new Quemado(CANTIDAD_JUGADORES);
	@Test
	public void alCrearElJuegoNoDeberiaHaberJugadoresQuemados(){
		assertTrue(juego.contarJugadoresQuemados(Equipo.AZUL) == 0);
		assertTrue(juego.contarJugadoresQuemados(Equipo.ROJO) == 0);
	}
	
	@Test
	public void alCrearElJuegoTodosLosJugadoresDeberianSerNoQuemados(){
		assertTrue(juego.obtenerCamisetasDeJugadoresNoQuemados(Equipo.AZUL).size() == CANTIDAD_JUGADORES);
		assertTrue(juego.obtenerCamisetasDeJugadoresNoQuemados(Equipo.ROJO).size() == CANTIDAD_JUGADORES);
	}
	
	@Test
	public void quemarJugadorDeberiaMarcarloComoQuemado(){
		juego.quemarJugardor(Equipo.AZUL, 3);
		assertTrue(juego.obtenerCamisetasDeJugadoresNoQuemados(Equipo.AZUL).size() == 9);
		assertTrue(!juego.obtenerCamisetasDeJugadoresNoQuemados(Equipo.AZUL).contains(3));
		assertTrue(juego.contarJugadoresQuemados(Equipo.AZUL) == 1);
	}
	
	@Test
	public void terminoDeberiaSerVerdaderoSiFueronQuemadosTodosLosJugadores(){
		for(int i = 1; i <= CANTIDAD_JUGADORES; i++){
			juego.quemarJugardor(Equipo.AZUL, i);
		}
		assertTrue(juego.termino());
	}
	
	@Test
	public void terminoDeberiaSerFalsoSiNoFueronQuemadosTodosLosJugadores(){
		juego.quemarJugardor(Equipo.AZUL, 2);
		juego.quemarJugardor(Equipo.AZUL, 4);
		juego.quemarJugardor(Equipo.AZUL, 5);
		juego.quemarJugardor(Equipo.ROJO, 2);
		juego.quemarJugardor(Equipo.ROJO, 4);
		assertFalse(juego.termino());
	}
	
	@Test
	public void obtenerGanadorDeberiaDevolverElEquipoQueNoTieneTodosSusJugadoresQuemados(){
		for(int i = 1; i <= CANTIDAD_JUGADORES; i++){
			juego.quemarJugardor(Equipo.AZUL, i);
		}
		assertTrue(juego.obtenerGanador().equals(Equipo.ROJO));
		assertTrue(juego.obtenerCamisetasDeJugadoresNoQuemados(Equipo.ROJO).size() >= 0);
	}
	
	@Test
	public void obtenerGanadorDeberiaDevolverNullSiElJuegoNoTermino(){
		juego.quemarJugardor(Equipo.AZUL, 2);
		juego.quemarJugardor(Equipo.AZUL, 4);
		juego.quemarJugardor(Equipo.AZUL, 5);
		juego.quemarJugardor(Equipo.ROJO, 2);
		juego.quemarJugardor(Equipo.ROJO, 4);
		assertNull(juego.obtenerGanador());
	}
}
