using MySqlConnector;
using System;
using System.Web;
using System.Web.Services;

namespace SeducaSOAP
{
    public class Calificacion
    {
        public int Id { get; set; }
        public long Ci { get; set; }
        public string Nombres { get; set; }
        public string Apellidos { get; set; }
        public bool EsBachiller { get; set; }

        public Calificacion() { }

        public Calificacion(int id, long ci, string nombres, string apellidos, bool esBachiller)
        {
            this.Id = id;
            this.Ci = ci;
            this.Nombres = nombres;
            this.Apellidos = apellidos;
            this.EsBachiller = esBachiller;
        }
    }

    /// <summary>
    /// Descripción breve de Seduca
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class Seduca : System.Web.Services.WebService
    {
        private const string DatosBd = "Server=localhost;Database=bd_seduca;Uid=root;Pwd=;";

        [WebMethod]
        public Calificacion ObtenerCalificaciones(long ci)
        {
            try
            {
                Calificacion calificacion = null;
                using (var conexion = new MySqlConnection(DatosBd))
                {
                    conexion.Open();
                    using (var comando = new MySqlCommand("SELECT * FROM PERSONAS WHERE ci = @ci", conexion))
                    {
                        comando.Parameters.AddWithValue("@ci", ci);
                        using (var reader = comando.ExecuteReader())
                        {
                            if (reader.Read())
                            {
                                int id = reader.GetInt32("id");
                                long cedula = reader.GetInt64("ci");
                                string nombres = reader.GetString("nombres");
                                string apellidos = reader.GetString("apellidos");
                                bool esBachiller = reader.GetString("es_bachiller").ToLower() == "true";

                                calificacion = new Calificacion(id, cedula, nombres, apellidos, esBachiller);
                            }
                        }
                    }
                }
                return calificacion;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error al obtener las calificaciones: {ex.Message}");
            }
        }
    }
}
