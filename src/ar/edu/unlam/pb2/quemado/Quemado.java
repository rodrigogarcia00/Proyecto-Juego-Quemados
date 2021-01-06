package ar.edu.unlam.pb2.quemado;

import java.util.ArrayList;

public class Quemado {

	private ArrayList<Boolean> equipoRojo;
	private ArrayList<Boolean> equipoAzul;
	
	public Quemado(Integer jugadoresPorEquipo) {
		equipoRojo = new ArrayList<Boolean>(jugadoresPorEquipo);
		for(int i = 0; i < jugadoresPorEquipo; i++){
			equipoRojo.add(Boolean.FALSE);
		}
		equipoAzul = new ArrayList<Boolean>(jugadoresPorEquipo);
		for(int i = 0; i < jugadoresPorEquipo; i++){
			equipoAzul.add(Boolean.FALSE);
		}
	}

	public Integer contarJugadoresQuemados(Equipo equipo) {
		ArrayList<Boolean> team = obtenerEquipo(equipo);
		Integer jugadoresQuemados = 0;
		for(int i = 0; i < team.size(); i++){
			if(team.get(i)){
				jugadoresQuemados++;
			}
		}
		return jugadoresQuemados;
	}

	public ArrayList<Integer> obtenerCamisetasDeJugadoresNoQuemados(Equipo equipo) {
		ArrayList<Boolean> team = obtenerEquipo(equipo);
		ArrayList<Integer> resultado = new ArrayList<Integer>(team.size());
		for(int i = 0; i < team.size(); i++){
			if(!team.get(i)){
				resultado.add(i+1);
			}
		}
		return resultado;
	}
	
	private ArrayList<Boolean> obtenerEquipo(Equipo equipo){
		if(equipo.equals(Equipo.AZUL)){
			return equipoAzul;
		}else {
			return equipoRojo;
		}
	}

	public void quemarJugardor(Equipo equipo, int numeroCamiseta) {
		ArrayList<Boolean> team = obtenerEquipo(equipo);
		team.remove(numeroCamiseta-1);
		team.add(numeroCamiseta-1, Boolean.TRUE);
	}

	public boolean termino() {
		return !equipoRojo.contains(Boolean.FALSE) 
				|| !equipoAzul.contains(Boolean.FALSE);
		
	}

	public Equipo obtenerGanador() {
		if(termino()){
			if(!equipoRojo.contains(Boolean.FALSE)){
				return Equipo.AZUL;
			}else{
				return Equipo.ROJO;
			}
		}
		return null;
	}

}
