package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.*;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;

public class AnimalMain {
	public static int mostrarMenu() {
		System.out.println("1. Mostrar animales");
		System.out.println("2. Añadir animales");
		System.out.println("3. Modificar la edad de un animal por su id");
		System.out.println("4. Eliminar un animal por su id");
		System.out.println("5. Salir");
		return Utilidades.leerInt();
	}

	public static void mostrarAnimales(ObjectInputStream ois, File fich) {
		boolean finArchivo = false;
		if (fich.exists()) 
		{
			try {
				ois = new ObjectInputStream(new FileInputStream(fich));

				// Leer mientras no se alcance el fin del archivo
				while (!finArchivo) {
					try {
						Animal aux = (Animal) ois.readObject();
						System.out.println(aux.toString());
					} catch (EOFException e) {
						// Fin del archivo alcanzado
						finArchivo = true;
					}
				}
				ois.close();

			} catch (Exception e) {
				System.out.println("Fatal error");
			} 
		}else
		{
			System.out.println("Fichero nuevo");
		}
	}
	public static void añadir(File fich) {
		int mas;
		int edad;	
		String id;
		String nombre;
		
		if (fich.exists()) 
		{
			System.out.println("El fichero ya existe, se añadirán al final");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(fich, true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyObjectOutputStream moos = null;
			try {
				moos = new MyObjectOutputStream(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			do {
				System.out.println("Introduce el Id unico");
				id = Utilidades.introducirCadena();
				
				System.out.println("Introduce el nombre:");
				nombre = Utilidades.introducirCadena();
				
				System.out.println("Introduce la edad");
				edad = Utilidades.leerInt();
				
				Animal a = new Animal(id,nombre,edad);
				try {
					moos.writeObject(a);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Hay mas personas? (1=Si/2=No)");
				mas = Utilidades.leerInt(1, 2);
			} while (mas == 1);
			try {
				moos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else
		{
			System.out.println("Primero añade el fichero");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		File fich = new File("animales.dat");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				mostrarAnimales(ois, fich);
				break;
			case 2:
				añadir(fich);
				break;
			case 3:

				break;
			case 4:

				break;
			}
		} while (opcion != 5);
	}

}
