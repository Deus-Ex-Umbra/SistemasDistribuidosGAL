using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.SqlClient;
using MySqlConnector.Authentication;
using MySqlConnector.Logging;
using MySqlConnector;


namespace Pronóstico_SOAP
{
    /// <summary>
    /// Descripción breve de pronostico
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class pronostico : System.Web.Services.WebService
    {
        private const string datos_bd = "Server=localhost;Database=bd_bcb;Uid=root;Pwd=;"; 
        private object coneccion = new MySqlConnection(string.Format(datos_bd));
        [WebMethod]
        public List<Cotizacion> obtenerCotizaciones(DateTime fecha)
        {
            try
            {
                List<Cotizacion> cotizaciones = new List<Cotizacion>();
                using (var conexion = new MySqlConnection(datos_bd))
                {
                    conexion.Open();
                    using (var comando = new MySqlCommand("SELECT * FROM cotizacion WHERE fecha = @fecha", conexion))
                    {
                        comando.Parameters.AddWithValue("@fecha", fecha);
                        using (var reader = comando.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                cotizaciones.Add(new Cotizacion
                                {
                                    fecha = reader.GetDateTime("fecha").ToString("yyyy-MM-dd"),
                                    cotizacion = reader.GetDouble("cotizacion"),
                                    cotizacion_oficial = reader.GetDouble("cotizacion_oficial")
                                });
                            }
                        }
                    }
                    return cotizaciones;
                }
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener las cotizaciones");
            }
        }

        [WebMethod]
        public string registrarCotizacion(DateTime fecha, double cotizacion, double cotizacion_oficial)
        {
            try
            {
                using (var conexion = new MySqlConnection(datos_bd))
                {
                    conexion.Open();
                    using (var comando = new MySqlCommand("INSERT INTO cotizacion (fecha, cotizacion, cotizacion_oficial) VALUES (@fecha, @cotizacion, @cotizacion_oficial)", conexion))
                    {
                        comando.Parameters.AddWithValue("@fecha", fecha);
                        comando.Parameters.AddWithValue("@cotizacion", cotizacion);
                        comando.Parameters.AddWithValue("@cotizacion_oficial", cotizacion_oficial);
                        comando.ExecuteNonQuery();
                    }
                }
                return "Cotización registrada";
            }
            catch (Exception ex)
            {
                return "Error al registrar la cotización";
            }
        }
    }
    public class Cotizacion
    {
        public string fecha { get; set; }
        public double cotizacion { get; set; }
        public double cotizacion_oficial { get; set; }
    }
}
