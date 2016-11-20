/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    
    private boolean respuestaAutomatica; // atributo para guardar la respuesta automática
    private String miTo; // la creo para intentar que salga el destinatario en getNextMailItem
    private String mensajeAutomatico;   //mensaje automatico 
    private String asuntoAutomatico;   //mensaje automatico 

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        miTo = "";
        mensajeAutomatico = "Estamos de vacaciones, disculpe las molestias";
        asuntoAutomatico = "Vacaciones";
    }

    /**
     * No consigo que cuando se llame a este metodo devuelva el destinatario antiguo como remitente.
     */
    public void getNextMailItem()
    {

        if(respuestaAutomatica){
         MailItem item2 = new MailItem(user,miTo,asuntoAutomatico,mensajeAutomatico); 
         miTo = item2.getFrom();    // miTo no consigo ver el remitente
         MailItem item = new MailItem(user,miTo,asuntoAutomatico,mensajeAutomatico); 

          sendMailItem(miTo,asuntoAutomatico,mensajeAutomatico); 
          item.print();         
        }
        else{
         System.out.println("Error.Activa el modo automático");          
        }
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String asunto, String message)
    {   
        miTo = to; // la creo para intentar que salga el destinatario en getNextMailItem

        MailItem item = new MailItem(user, miTo, asunto, message);
        server.post(item);
    }
    
        /**
         * Se cuantos e-mail estan pendientes de leer
     */
    public void numeroDeEmailServidor(){

        System.out.println("Número de correos pendientes: " + server.howManyMailItems(user));
    
    }
    
     /**
     * Activa o desactiva el correo automático
     * //Escribe true para activar los correos automáticos, false para desactivarlos.
     */
    public void respuestaAutomatica(boolean respuestaAutomatica)
    {
        this.respuestaAutomatica = respuestaAutomatica;
    }
    
     /**
     * Cambia el mensaje de la respuesta automatica
     * //Escribe entre "" el nuevoasunto y el nuevo mensaje.
     */
    public void cambiarRespuestaAutomatica(String NuevoAsuntoAutomatico, String NuevoMensajeAutomatico)
    {
        asuntoAutomatico = NuevoAsuntoAutomatico;
        mensajeAutomatico = NuevoMensajeAutomatico;   
    }
}

