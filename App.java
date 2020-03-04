package com.mike.Lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
	private static List<User> users;

	private static void setUpUser() {

		users = new ArrayList<User>();

		users.add(new User(1, "Fran"));
		users.add(new User(2, "Nuria"));
		users.add(new User(3, "Ana"));
		users.add(new User(4, "Cristian"));
		users.add(new User(5, "Aleix"));
	}

	private static void printList(List<User> lista) {
		for (User s : lista) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		setUpUser();
		printList(users);

		// No es comun
		Stream stream = Stream.of(users);

		// Lo mismo pero mas simple
		users.stream();

		// Imprimir con lambdas
		users.stream().forEach(e -> e.setNombre(e.getNombre() + " Apellido")); // Añade Apellido al nombre de la lista
		// users.stream().forEach(e -> System.out.println(e));

		// Map y Collector.toList
		// Map -> Permite realizar una transformacion rapida de la lista original
		// Collector.toList -> permite sacar cosas del stream una vez finalizado el
		// proceso

		List<String> nombres = users.stream().map(e -> e.getNombre()).collect(Collectors.toList());

		// nombres.stream().forEach(e -> System.out.println(e));

		// Filters
		System.out.println("-------------Filters---------------");
		List<User> usuariosFiltrados = users.stream().filter(e -> e.getNombre().charAt(0) == 'A')
				.filter(e -> e.getNombre().length() > 13).collect(Collectors.toList());
		// Imprimir con funcion

		users.stream().filter(e -> e.getNombre().charAt(0) == 'A').filter(e -> e.getNombre().length() > 13)
				.forEach(e -> System.out.println(e));

		
		
		System.out.println("----------------------FindFirst-------------------------");
		// Nos vamos a quedar con el primer usuario cuyo id sea par
		// Uso de Optional (no muy recomendable)
		Optional<User> user = users.stream()
				.filter(e -> e.getId() % 2 == 0)
				.findFirst();
		
		if (user.isPresent()) {
			System.out.println(user.get().getNombre());
		}

		
		// Nos vamos a quedar con el primer usuario cuyo id sea par
		// Uso de orElse (mejor)
		User user2 = users.stream()
				.filter(e -> e.getId() % 7 == 0)
				.findFirst()
				.orElse(null); //Valor por defecto si no encuentra, podria ser .orElse(7,"Michael")
		
		//System.out.println(user2.getNombre());
		
		
		// El objetivo de FlatMap es juntar lista de una lista de listas
		System.out.println("------------------FlatMap-----------------------");
		List<String> nombresSantander = new ArrayList<String>();
		nombresSantander.add("Fran");
		nombresSantander.add("Paco");
		nombresSantander.add("Dani");
		List<String> nombresSabadell = new ArrayList<String>();
		nombresSabadell.add("Luis");
		nombresSabadell.add("Michael");
		nombresSabadell.add("Daniel");
		
		List<List<String>> nombresTotales = new ArrayList<List<String>>();
		nombresTotales.add(nombresSantander);
		nombresTotales.add(nombresSabadell);

		List<String> nombresFusionados = nombresTotales.stream()
		.flatMap(e -> e.stream())
		.collect(Collectors.toList());
		
		nombresFusionados.stream().forEach(e->System.out.println(e));
		
		
		// Es como forEach pero sin ser final (despues del forEach, no te deja hacer nada mas, con este si)
		System.out.println("-------------------Peek------------------------");
		List<User> fusionApellido = users.stream()
				.peek(e -> e.setNombre(e.getNombre() + " Apellido"))
				.collect(Collectors.toList());
		fusionApellido.stream().forEach(e->System.out.println(e));

		
		
		System.out.println("--------------Count-------------------");
		setUpUser();
		long cantidadResultados = users.stream()
				.filter(e -> e.getNombre().length()>5)
				.count();
		System.out.println(cantidadResultados);
		
		
		// Salta un numero de elementos y limita la cantidad de resultados
		System.out.println("---------------------Skip y Limit------------------------");
		List<String> subLista = nombresFusionados.stream()
				.skip(2)
				.limit(3)
				.collect(Collectors.toList());
		subLista.stream().forEach(e->System.out.println(e));

		
		// Ordena los elementos
		setUpUser();
		System.out.println("-----------------Sorted-------------------------");
		// Nueva lista de users ordenados por nombre
		List<User> usuariosPorNombre = users.stream()
				.sorted(Comparator.comparing(User::getNombre))
				.collect(Collectors.toList());
		usuariosPorNombre.stream().forEach(e->System.out.println(e));
		
		
		// Ver el minimo y maximo de la lista
		System.out.println("-------------------------- Min y Max --------------------------------------");
		setUpUser();
		User minimoId = users.stream()
				.min(Comparator.comparing(User::getId))
				.orElse(null);
		
		User maximoId = users.stream()
				.max(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(minimoId.getId() + " " + maximoId.getId());


		//
		System.out.println("-------------------------- Distinct --------------------------------------");
		List<String> nombres2 = new ArrayList<String>();
		nombres2.add("Fran");
		nombres2.add("Dani");
		nombres2.add("Paco");
		nombres2.add("Fran");
		
		List<String> nombresSinDuplicadosList = nombres2.stream()
				.distinct()
				.collect(Collectors.toList());
		nombresSinDuplicadosList.stream().forEach(e->System.out.println(e));
		
		
		
	}
}
