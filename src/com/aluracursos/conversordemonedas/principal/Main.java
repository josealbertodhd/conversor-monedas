package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.modelos.ConsultaConversion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaConversion consulta = new ConsultaConversion();
        String menu = """
                *******************************************************   
                Sea bienvenido/a al conversor de Moneda =]
                    
                1) Dolar ==> Peso argentino
                2) Peso argentino ==> Dolar
                3) Dolar ==> Real Brasileño
                4) Real Brasileño ==> Dolar
                5) Dolar ==> Peso Colombiano;
                6) Peso Colombiano => Dolar
                7) Salir
                Elija una opcion valida:
                *******************************************************
                """;

        try {
            int opcion = 0;
            do {
                System.out.println(menu);
                opcion = teclado.nextInt();
                float valorAConvertir = 0;
                float calculoIntercambio = 0;

                if (opcion != 7){
                    System.out.println("Ingrese el valor que desea convertir");
                    valorAConvertir = teclado.nextFloat();
                }

                switch (opcion){
                    case 1:
                        calculoIntercambio(valorAConvertir, consulta, "USD", "ARS");
                        break;
                    case 2:
                        calculoIntercambio(valorAConvertir, consulta, "ARS", "USD");
                        break;
                    case 3:
                        calculoIntercambio(valorAConvertir, consulta, "USD", "BRL");
                        break;
                    case 4:
                        calculoIntercambio(valorAConvertir, consulta, "BRL", "USD");
                        break;
                    case 5:
                        calculoIntercambio(valorAConvertir, consulta, "USD", "COP");
                        break;
                    case 6:
                        calculoIntercambio(valorAConvertir, consulta, "COP", "USD");
                        break;
                    case 7:
                        System.out.println("Finalizando el programa...");
                        opcion = 7;
                        break;
                    default:
                        System.out.println("!Digite una opcion valida¡");
                        break;
                }
            }while (opcion != 7);

        }catch (InputMismatchException e){
            System.out.println("!Asegurese de que este digitando una opcion valida¡");
        }
    }

    static void calculoIntercambio(float valor, ConsultaConversion consulta, String monedaOrigen, String monedaIntercambio){
        float calculoIntercambio = 0;
        if(monedaIntercambio.equals("ARS")){
            calculoIntercambio = valor * consulta.busquedaConversionTasa(monedaOrigen).getARS();
        }else if (monedaIntercambio.equals("COP")){
            calculoIntercambio = valor * consulta.busquedaConversionTasa(monedaOrigen).getCOP();
        } else if (monedaIntercambio.equals("BRL")) {
            calculoIntercambio = valor * consulta.busquedaConversionTasa(monedaOrigen).getBRL();
        }else{
            calculoIntercambio = valor * consulta.busquedaConversionTasa(monedaOrigen).getUSD();
        }

        System.out.println("El valor de " + valor + " [" + monedaOrigen + "] corresponde al valor final de "
                +  calculoIntercambio + " ["+ monedaIntercambio + "]");
    }


}