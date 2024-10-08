using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace Servicios
{
    /// <summary>
    /// Descripción breve de Operaciones
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class Operaciones : System.Web.Services.WebService
    {

        [WebMethod]
        public float add(float a, float b)
        {
            return a + b;
        }
        [WebMethod]
        public float substract(float a, float b)
        {
            return a - b;
        }
        [WebMethod]
        public float multiply(float a, float b)
        {
            return a * b;
        }
        [WebMethod]
        public float divide(float a, float b)
        {
            if (b == 0) throw new ArgumentException("División por cero");
            return a / b;
        }
    }
}
