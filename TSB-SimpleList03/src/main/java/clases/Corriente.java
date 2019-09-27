package clases;

/**
 * Representa una Cuenta Corriente sencilla en un Banco. Estas cuentas no generan inter�s, pero permiten librar cheques y eventualmente
 * girar en descubierto
 * 
 * @author Ing. Valerio Frittelli
 * @version Abril de 2004
 */
public class Corriente extends Cuenta
{
    private boolean descubierto;  // true si se autoriza girar en descubierto - false si no. 

	/**
	 * Constructor por defecto
	 */
	public Corriente()
	{
	}
	
    /**
     * Constructor con par�metros para CADA atributo. 
     */
	public Corriente(int num, float sal, boolean des)
	{
	    super(num, sal);   // invoca al constructor de la super clase con dos par�metros
	    descubierto = des;
	}
	
    /**
     *  M�todo de consulta para la autorizaci�n de giro en rojo...
     *  @return el estado de la autorizaci�n de giro en descubierto:  true: es v�lido el giro - false: no es v�lido
     */
    public boolean getDescubierto()
    {
       /* 
        * Cuando el atributo es boolean, se suele recomendar tambi�n que el m�todo de acceso se designe como "is" en lugar de "get" 
        * para mejorar la legibilidad del c�digo de llamada
        */
       return descubierto;   
    }
    
    /**
     *  M�todo modificador para la autorizaci�n de giro en rojo...
     *  @param des true: se autoriza giro en descubierto - false: no se autoriza
     */
    public void setDescubierto( boolean des )
    {
       descubierto = des;   
    }

    /**
     *  Redefinici�n del m�todo retirar heredado desde Cuenta. Si el importe a retirar es mayor que el saldo, a�n podr�a ser v�lido el 
     *  retiro si la cuenta tiene autorizaci�n de sobregiro.
     *  @param imp monto que ser� retirado de la cuenta
     */
    public void retirar (float imp)
    {
      float s = getSaldo();
      if (s >= imp || getDescubierto())
      {
         setSaldo(s - imp);   
      }
    }


    /** 
     *  Redefinici�n del m�todo toString
     *  @return el contenido del objeto en forma String con formato adecuado para ser visualizado
     */
    public String toString()
    { 
       // observar la forma de invocar a un m�todo de la super clase
       return super.toString() + " - Autorizaci�n de giro en rojo: " + descubierto;
    }
}
