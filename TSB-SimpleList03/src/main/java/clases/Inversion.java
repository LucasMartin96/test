package clases;

/**
 * Representa una cuenta de Inversion, como subclase de la clase Cuenta. Una cuenta de 
 * inversi�n da cierto monto de inter�s a su due�o, aunque no permite girar cheques ni es
 * de libre disponibilidad
 * 
 * @author Ing. Valerio Frittelli
 * @version Abril de 2004
 */
public class Inversion extends Cuenta
{
    private float tasa;  // la tasa de inter�s pactada para la cuenta. Se agrega a los atributos heredados desde Cuenta
    
    /**
     * Constructor por defecto. Si se invoca a este constructor, Java autom�ticamente invoca primero al constructor por defecto de la super
     * clase...
     */
	public Inversion()
	{
	}
	
    /**
     * Constructor con par�metros para CADA atributo. 
     */
	public Inversion(int num, float sal, float tas)
	{
	    super(num, sal);   // invoca al constructor de la super clase con dos par�metros
	    tasa = tas;
	}
	
    /**
     *  M�todo de consulta para la tasa de inter�s
     *  @return la tasa de inter�s pactada para la cuenta
     */
    public float getTasa()
    {
       return tasa;   
    }
    
    /**
     *  M�todo modificador para la tasa de inter�s
     *  @param tas la nueva tasa de inter�s de la cuenta
     */
    public void setTasa( float tas )
    {
       tasa = tas;   
    }

    /**
     * Actualiza el saldo de la cuenta en base a la tasa de inter�s pactada
     */
    public void actualizar ()
    {
       float monto = getSaldo() * tasa / 100;
       depositar(monto);
    }

   /**
    *  Retiro de cierto monto del saldo de la cuenta. 
    *  @param imp monto que ser� retirado de la cuenta
    */
    //*
    public void retirar (float imp)
    {
      float s = getSaldo();
      if (s - imp >= 0)
      {
         setSaldo(s - imp);   
      }
    }
    //*/

   
    /** 
     *  Redefinici�n del m�todo toString
     *  @return el contenido del objeto en forma String con formato adecuado para ser visualizado
     */
    public String toString()
    { 
       // observar la forma de invocar a un m�todo de la super clase
       return super.toString() + " - Tasa: " + tasa;
    }
}
