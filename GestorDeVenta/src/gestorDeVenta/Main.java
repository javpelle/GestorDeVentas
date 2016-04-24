package gestorDeVenta;

import swings.Swing;

public class Main {
	
	
	public static void main(String[] args) {
		
		String archivo = "datos.txt";
		Gestor gestor = new Gestor(archivo);
		new Swing (archivo, gestor);
	}
}
	
	/**
	 * 
		int menu;
		do {
			menu = menuInicio(gestor);
			if (menu != 0) {
				if (menu == 1) { // Seleccionar cliente
					int tecla;
					int numCliente = 0;
					do {
						mostrarClientes(gestor, numCliente);
						tecla = getKey();
						if (tecla == TE_Abajo && numCliente < gestor.clientes.contador) {
							numCliente++;
						}
						else if (tecla == TE_Arriba && numCliente > 0) {
							numCliente--;
						}
					} while (tecla != TE_Intro);
						if (numCliente != gestor.clientes.contador) {
							gestor.brutoTotal -= gestor.clientes.cliente[numCliente].dineroGastado;
							gestor.costes -= gestor.clientes.cliente[numCliente].coste;
							mostrarCliente(gestor.clientes.cliente[numCliente]);
							gestor.brutoTotal += gestor.clientes.cliente[numCliente].dineroGastado;
							gestor.costes += gestor.clientes.cliente[numCliente].coste;
							gestor.netoTotal = gestor.brutoTotal - gestor.costes;
						}	
				} else if (menu == 2) crearCliente(gestor); // crearCuenta(*sesion.gestor); // CREAR CUENTA
				else { // menu == 3
					cout << "Introduce un nombre a buscar: ";
					string nombreAux;
					cin.ignore();
					getline(cin, nombreAux);
					int pos;
					buscar(gestor.clientes, nombreAux, pos);
					if (pos == gestor.clientes.contador) pos--;
					gestor.brutoTotal -= gestor.clientes.cliente[pos].dineroGastado;
					gestor.costes -= gestor.clientes.cliente[pos].coste;
					mostrarCliente(gestor.clientes.cliente[pos]);
					gestor.brutoTotal += gestor.clientes.cliente[pos].dineroGastado;
					gestor.costes += gestor.clientes.cliente[pos].coste;
					gestor.netoTotal = gestor.brutoTotal - gestor.costes;
				}
			}
		} while (menu != 0);
		apagar(gestor, nombreArchivo);
		// iniciarSesion(sesion, gestor, usuario);
		return 0;
	 */