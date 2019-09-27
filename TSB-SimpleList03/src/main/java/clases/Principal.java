package clases;

public class Principal
{
    public static void main(String args[])
    {
        // creamos una lista para guardar cuentas de Inversion
        TSBSimpleList lis1 = new TSBSimpleList();
        lis1.addInOrder( new Inversion (101, 2000, 2.1f) );
        lis1.addInOrder( new Inversion (212, 1000, 1.2f) ); 
        lis1.addInOrder( new Inversion (511, 3000, 3) );   
        System.out.println( "\nLista de Cuentas: " + lis1 );
        
        // creamos una lista para guardar Clientes
        TSBSimpleList lis2 = new TSBSimpleList();
        lis2.addFirst( new Cliente(10, "Juan" ) );
        lis2.addLast( new Cliente(20, "Ana" ) ); 
        lis2.add( 1, new Cliente(15, "Pedro" ) );              
        System.out.println( "\nLista de Clientes: " + lis2 );
        
        // creamos una lista para guardar Strings
        TSBSimpleList lis3 = new TSBSimpleList();
        lis3.add( 0, "Talleres" );
        lis3.add( 1, "Belgrano" ); 
        lis3.add( 2, "Instituto" );              
        System.out.println( "\nLista de Strings: " + lis3 );
                
        // ahora recorremos la lista de Cuentas de Inversion para calcular el saldo promedio...
        if( lis1.isEmpty() ) System.out.println( "\nNo hay cuentas en la lista..." );
        else
        {
            float a = 0;
            int b = 0;
            for( int i = 0; i < lis1.size(); i++ )
            {
                Inversion x = ( Inversion ) lis1.get( i );   
                a += x.getSaldo();
                b++;
            }
            float prom = 0;
            if( b != 0 ) prom = a / b;
            System.out.println( "\nSaldo promedio de las cuentas de Inversion: " + prom );
        }
        
        // Buscar si la lista de Strings contiene el nombre de un club grande...
        int index = lis3.indexOf( "Talleres" );
        if( index != -1 )
        {
            System.out.println("\nClub grande encontrado en la posicion: " + index );
            System.out.println("Datos del club: " + lis3.get( index) );
        }
        else System.out.println( "\nNo hay clubes grandes en la lista de Strings..." );
        
        // Mostrar los nombres y solo los nombres de los Clientes de lista lils2...
        if( lis2.isEmpty() ) System.out.println( "\nNo hay clientes registrados..." );
        else
        {
            System.out.println( "\nNombres de los Clientes registrados: " );
            for( int i = 0; i < lis2.size(); i++ )
            {
                Cliente c = ( Cliente ) lis2.get( i );   
                System.out.println( c.getNombre() );
            }            
        }
        
        // una lista de numeros... 
        TSBSimpleList d = new TSBSimpleList();
        d.addFirst(4);  // aca esta haciendo auto-boxing...
        d.addLast(new Integer(6));
        d.addLast(8);
        
        int x = (Integer) d.getFirst(); // auto-deboxing...
        int y = (Integer) d.getLast(); // otra forma de autodeboxing...
        System.out.println( "\nLista de numeros: " + d );   
    }
}
