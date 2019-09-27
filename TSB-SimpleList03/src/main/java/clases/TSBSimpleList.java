package clases;

import java.util.NoSuchElementException;
public class TSBSimpleList
{
    // la direccion del primer nodo de la lista
    private TSBNode frente;

    // la cantidad de nodos de la lista
    private int cantidad;

    /** 
     * Constructor por defecto.
     */
    public TSBSimpleList ()
    {
        frente = null;
        cantidad = 0;
    }

    /**
     *  Agrega el objeto x en la posicion index de la lista. Los objetos 
     *  que se encontraban en la lista a partir de esa posicion, se 
     *  desplazan hacia la derecha. Se toma como convencion que el primer 
     *  nodo esta en la posicion cero. Note que el metodo puede agregar un
     *  elemento en una lista vacia, o un elemento al final de la lista, pero
     *  no puede insertar un elemento "dejando nodos en blanco"...
     *  @param index el numero de orden del objeto a acceder.
     *  @param x el objeto a almacenar en la posicion index.
     *  @throws IndexOutOfBoundsException si index esta fuera de rango (index <0 || index > size() ).
     *  @throws ClassCastException si x no es compatible con el contenido de la lista.
     */
    public void add( int index, Comparable x )
    {
        if( ! isHomogeneus( x ) ) throw new ClassCastException( "Objeto incompatible" );
        if( index < 0 || index > size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );

        TSBNode nuevo = new TSBNode( x, frente );
        if( index == 0 ) frente = nuevo;
        else
        {
          TSBNode p = frente;
          for( int i = 0; i < index - 1; i++ ) { p = p.getNext(); }
          nuevo.setNext( p.getNext() );
          p.setNext( nuevo );
        }         
        
        cantidad++;
    }    

    /**
     *  Inserta un objeto al principio de la lista. La insercion se hara solo 
     *  si el parametro recibido no es null y si el objeto representado es 
     *  compatible con el contenido actual de la lista.
     *  @param x el objeto a almacenar en la lista.
     */
    public void addFirst( Comparable x )
    {
          if ( ! isHomogeneus( x ) ) return;

          TSBNode p = new TSBNode(x, frente);
          frente = p;
          cantidad++;
    }  

    /**
     *  Inserta un objeto en forma ordenada en la lista. La insercion se 
     *  hara solo si el parametro recibido no es null y si el objeto representado 
     *  es compatible con el contenido actual de la lista. Se supone que la lista 
     *  esta ya ordenada (es decir, se supone que todas las inserciones fueron 
     *  realizadas llamando a este metodo). 
     *  @param x el objeto a almacenar en la lista.
     */
    public void addInOrder(Comparable x)
    {
          if ( ! isHomogeneus( x ) ) return;

          TSBNode nuevo = new TSBNode( x, null );
          TSBNode p = frente, q = null;
          while ( p != null && x.compareTo( p.getInfo() ) >= 0 )
          {
              q = p;
              p = p.getNext();
          }
          nuevo.setNext( p );
          if( q != null ) q.setNext( nuevo );
          else frente = nuevo;
          cantidad++;
    }             

    /**
     *  Inserta un objeto al final de la lista. La insercion se hara solo 
     *  si el parametro recibido no es null y si el objeto representado es 
     *  compatible con el contenido actual de la lista.
     *  @param x el objeto a almacenar en la lista.
     */
    public void addLast( Comparable x )
    {
          if ( ! isHomogeneus( x ) ) return;

          TSBNode nuevo = new TSBNode( x, null );
          TSBNode p = frente;
          while ( p != null && p.getNext() != null ) { p = p.getNext(); }
          if( p != null ) p.setNext( nuevo );
          else frente = nuevo;
          cantidad++;
    }  

    public void clear( )
    {
       frente = null; 
       cantidad = 0;
    }

    /**
     *  Determina si en la lista existe un elemento que coincida con x. 
     *  Usamos compareTo() para realizar las comparaciones (aunque podria 
     *  usarse equals()).
     *  @return true si x esta en la lista - false si x no esta o si x es null.
     *  @param x el objeto a buscar.
     */
    public boolean contains( Comparable x )
    {
        if ( ! isHomogeneus( x ) ) return false;

        TSBNode p = frente;
        while ( p != null && x.compareTo( p.getInfo() ) != 0 ) { p = p.getNext(); }    
        return ( p != null );
    }

    /**
     *  Retorna (pero sin removerlo) el objeto ubicado en la posicion "index" 
     *  de la lista, tomando como convencion que el primer nodo esta en la 
     *  posicion cero.
     *  @param index el numero de orden del objeto a acceder.
     *  @return una referencia objeto ubicado en la posicion "index".
     *  @throws IndexOutOfBoundsException si index esta fuera de rango.
     */
    public Comparable get( int index )
    {
        if( index < 0 || index >= size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );

        TSBNode p = frente;
        for( int i = 0; i < index; i++ ) { p = p.getNext(); }
        return p.getInfo();
    }

    /**
     *  Retorna (pero sin removerlo) el objeto ubicado al principio de la lista. 
     *  @return una referencia al primer elemento de la lista.
     *  @throws NoSuchElementException si la lista estaba vacia.
     */
    public Comparable getFirst()
    {
       if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
       return frente.getInfo();
    }

    /**
     *  Retorna (pero sin removerlo) el objeto ubicado al final de la lista. 
     *  @return una referencia al primer elemento de la lista.
     */
    public Comparable getLast()
    {
       if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
       TSBNode p = frente;
       while( p.getNext() != null ) { p = p.getNext(); }
       return p.getInfo();
    }

    /**
     * Retorna el indice de la primera ocurrencia del objeto x en la lista, o -1 si x 
     * no esta en la lista o si x es null o si x no es compatible con el contenido de 
     * la lista.
     * @param x el objeto a buscar en la lista.
     * @return el indice de la primera ocurrencia de x en la lista, o -1 si x no existe.
     */
    public int indexOf( Comparable x )
    {
          if ( ! isHomogeneus( x ) )  return -1;

          int c = 0;
          for ( TSBNode p = frente; p != null; p = p.getNext() )
          {
              if( x.compareTo( p.getInfo() ) == 0 ) return c;
              c++;
          }
          return -1;
    }

    /**
     * Retorna true si la lista esta vacia.
     * @return true si la lista esta vacia - false en caso contrario.
     */
    public boolean isEmpty()
    {
       return (frente == null);    
    }

    /**
     *  Remueve el objeto x en la posicion index de la lista. Los objetos 
     *  que se encontraban a su derecha en la lista se desplazan hacia la 
     *  izquierda una posicion. Se toma como convencion que el primer 
     *  nodo esta en la posicion cero. 
     *  @param index el numero de orden del objeto a acceder.
     *  @return el objeto que se encontraba en la posicion index.
     *  @throws IndexOutOfBoundsException si index esta fuera de rango (index <0 || index >= size() ).
     */
    public Comparable remove( int index )
    {
        if( index < 0 || index >= size() ) throw new IndexOutOfBoundsException( "Indice fuera del rango" );

        TSBNode p = frente, q = null;
        for( int i = 0; i < index; i++ ) 
        {
            q = p;
            p = p.getNext();
        }

        Comparable x = p.getInfo();
        if( q == null ) frente = p.getNext();
        else q.setNext( p.getNext() );

        cantidad--;
        return x; 
    }

    /**
     *  Remueve el primer nodo de la lista que contenga al objeto x si el mismo se 
     *  encontraba en ella. Retorna true si la eliminacion tuvo exito, o false en 
     *  caso contrario (x no estaba en la lista, x era null, o x no era compatible 
     *  con el contenido de la lista).
     *  @param x el objeto a remover de la lista.
     *  @return true si la eliminacion pudo hacerse, false en caso contrario.
     */
    public boolean remove( Comparable x )
    {
        if( ! isHomogeneus( x ) ) return false;

        TSBNode p = frente, q = null;
        while( p != null && x.compareTo( p.getInfo() ) != 0  ) 
        {
            q = p;
            p = p.getNext();
        }

        if( p == null ) return false;

        if( q == null ) frente = p.getNext();
        else q.setNext( p.getNext() );      
        cantidad--;

        return true;
    }

    /**
     *  Retorna (y remueve) el objeto ubicado al principio de la lista. 
     *  @return el primer elemento de la lista.
     *  @throws NoSuchElementException si la lista estaba vacia.
     */
    public Comparable removeFirst()
    {
       if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");

       Comparable x = frente.getInfo();
       frente = frente.getNext();

       cantidad--;
       return x;
    }

    /**
     *  Remueve el primer nodo de la lista que contenga al objeto x si el mismo se 
     *  encontraba en ella. Equivale a invocar a remove(x). Retorna true si la 
     *  eliminacion tuvo exito, o false en caso contrario (x no estaba en la lista, 
     *  x era null, o x no era compatible con el contenido de la lista).
     *  @param x el objeto a remover de la lista.
     *  @return true si la eliminacion pudo hacerse, false en caso contrario.
     */
    public boolean removeFirstOccurrence( Comparable x )
    {
        return remove(x);
    }

    /**
     *  Retorna (y remueve) el objeto ubicado al final de la lista. 
     *  @return el ultimo elemento de la lista.
     *  @throws NoSuchElementException si la lista estaba vacia.
     */
    public Comparable removeLast()
    {
       if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");

       TSBNode p = frente, q = null;
       while( p.getNext() != null )
       {
          q = p;
          p = p.getNext();
       }
       Comparable x = p.getInfo();
       if( q != null ) q.setNext( p.getNext() );
       else frente = p.getNext();

       cantidad--;
       return x;
    }

    /**
     * Busca un objeto x en la lista, y en caso de encontrarlo retorna 
     * una referencia al objeto que ESTA EN LA LISTA. Retorna null si x 
     * no esta en la lista o si x es null o si x no es compatible con el 
     * contenido de la lista.
     * @param x el objeto a buscar en la lista.
     * @return una referencia al objeto encontrado en la lista.
     */
    public Comparable search (Comparable x)
    {
          if ( ! isHomogeneus( x ) )  return null;

          for ( TSBNode p = frente; p != null; p = p.getNext() )
          {
              if( x.compareTo( p.getInfo() ) == 0 ) return p.getInfo();
          }
          return null;
    }

    /**
     *  Reemplaza el objeto ubicado en la posicion "index" de la lista, por 
     *  el objeto x que entra como parametro. Se toma como convencion que el
     *  primer nodo esta en la posicion cero. El metodo retorna el objeto que
     *  se encontraba previamente en la posicion index.
     *  @param index el numero de orden del objeto a acceder.
     *  @param x el objeto a almacenar en la posicion index.
     *  @return el objeto previamente ubicado en la posicion index.
     *  @throws IndexOutOfBoundsException si index esta fuera de rango.
     *  @throws ClassCastException si x no es compatible con el contenido de la lista.
     */
    public Comparable set( int index, Comparable x )
    {
        if( ! isHomogeneus( x ) ) throw new ClassCastException( "Objeto incompatible" );
        if( index < 0 || index >= size() ) throw new NoSuchElementException( "Indice fuera del rango" );

        TSBNode p = frente;
        for( int i = 0; i < index; i++ ) { p = p.getNext(); }

        Comparable ant = p.getInfo();
        p.setInfo( x );
        return ant;
    }

    /**
     *  Retorna la cantidad de objetos que contiene la lista.
     *  @return la longitu de de la lista.
     */
    public int size()
    {
        return cantidad;
    }

    /**
     *  Redefine el metodo toString heredado desde Object.
     *  @return el contenido de la lista convertido a String.
     */
    @Override
    public String toString()
    {
           StringBuilder res = new StringBuilder( "[ " );
           for( TSBNode p = frente; p != null;  p = p.getNext() )
           {
              res.append( p.toString() );
              if ( p.getNext() != null ) res.append( " - " );
           }
           res.append( " ]" );
           return res.toString();
    }

    // Este metodo controla que x sea homogeneo con el contenido de la lista
    // Retorna true si es homogeneo y false en caso contrario
    private boolean isHomogeneus (Comparable x)
    {
          if ( x == null ) return false;
          if ( frente != null && x.getClass() != frente.getInfo().getClass() ) return false;
          return true;
    }
}
