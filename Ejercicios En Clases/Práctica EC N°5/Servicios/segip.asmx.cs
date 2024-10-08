using MySqlConnector;
using System;

using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace Servicios
{
    /// <summary>
    /// Descripción breve de segip
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class segip : System.Web.Services.WebService
    {

        private string connectionString = System.Configuration.ConfigurationManager.ConnectionStrings["BDSEGIP"].ConnectionString;

        [WebMethod]
        public Persona BuscarPersonaCI(string NumeroDocumento)
        {
            Persona persona = null;
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                MySqlCommand cmd = new MySqlCommand("SELECT * FROM Persona WHERE ci = @ci", conn);
                cmd.Parameters.AddWithValue("@ci", NumeroDocumento);
                MySqlDataReader reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    persona = new Persona
                    {
                        Id = (int)reader["id"],
                        CI = (string)reader["ci"],
                        Nombres = (string)reader["nombres"],
                        PrimerApellido = (string)reader["p_apellido"],
                        SegundoApellido = (string)reader["s_apellido"]
                    };
                }
            }
            return persona;
        }

        [WebMethod]
        public Persona[] BuscarPersonas(string PrimerApellido, string SegundoApellido, string Nombres)
        {
            List<Persona> personas = new List<Persona>();
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                MySqlCommand cmd = new MySqlCommand("SELECT * FROM Persona WHERE primer_apellido = @primer_apellido AND segundo_apellido = @segundo_apellido AND nombres = @nombres", conn);
                cmd.Parameters.AddWithValue("@primer_apellido", PrimerApellido);
                cmd.Parameters.AddWithValue("@segundo_apellido", SegundoApellido);
                cmd.Parameters.AddWithValue("@nombres", Nombres);
                MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    personas.Add(new Persona
                    {
                        Id = (int)reader["id"],
                        CI = (string)reader["ci"],
                        Nombres = (string)reader["nombres"],
                        PrimerApellido = (string)reader["primer_apellido"],
                        SegundoApellido = (string)reader["segundo_apellido"]
                    });
                }
            }
            return personas.ToArray();
        }
    }
}

public class Persona
{
    public int Id { get; set; }
    public string CI { get; set; }
    public string Nombres { get; set; }
    public string PrimerApellido { get; set; }
    public string SegundoApellido { get; set; }
}