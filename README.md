En esta entrega hemos ampliado el proyecto anterior, centrándonos principalmente en tres grupos de clases: Usuarios, Productos y Tickets.
Los usuarios pueden ser Clientes o Cajeros, cada uno con atributos propios que los diferencian. Los clientes almacenan los tickets que compran, mientras que los cajeros son los encargados de crearlos (lo que implica que, si un cajero es eliminado, también se eliminan todos sus tickets).
Los productos se dividen en distintos tipos: Comida, Reunión (ambos considerados productos por persona), Personalizado y Producto Regular, que es el producto común. Existe una clase abstracta llamada Producto, de la cual heredan la mayoría de estas clases. Los productos personalizables contienen además una lista de textos que se utiliza para aplicar descuentos y restricciones.
Los Tickets contienen una lista de productos que serán comprados y mantienen un estado (EMPTY, OPEN, CLOSED) que determina cómo se gestionan. Cada ticket posee un identificador que puede ser generado automáticamente o definido según la situación. Todas las clases modelo mencionadas disponen de sus propios controladores, encargados de gestionar altas, bajas y otras operaciones.
Hemos aplicado el patrón Command para manejar todas las operaciones del sistema. Para ello, definimos la interfaz ICommand, que contiene tres métodos: getPrimerArgumento(), getSegundoArgumento() y execute(). Cada comando los implementa según su funcionalidad. Todos estos comandos son gestionados por un controlador central, CommandController, que permite ejecutarlos desde el CLI y mostrarlos finalmente en App2.
𝙉𝙐𝙀𝙎𝙏𝙍𝙊 𝙐𝙈𝙇
Debido a que la aplicación contiene muchas clases ya que hemos aplicado el patrón Command, decidimos dividir el UML en cuatro diagramas, cada uno centrado en un área específica del sistema:
1.Usuarios y sus comandos (Clientes y Cajeros).
2.Productos y sus comandos.
3.Tickets y sus comandos.
4.Vista general, en la que se muestra explícitamente el CLI junto con sus controladores, además de los tres comandos independientes que no dependen de ninguna clase modelo.
