using System;
using System.Threading.Tasks;
using System.Net.Http;
using System.Text;
using System.Collections.Generic;
using System.Text.Json;
using Newtonsoft.Json;

namespace AppCotizacion
{
    internal class ServicioGraphQL
    {
        private const string BaseEndpoint = "http://127.0.0.1:8000/graphql";

        public async Task<List<Cotizacion>> ObtenerCotizacion(DateTime fecha)
        {
            try {
                string query = @"
                query ($fecha: String!) {
                    obtenerCotizacion(fecha: $fecha) {
                        id
                        fecha
                        cotizacion
                        cotizacion_oficial
                    }
                }";

                string formattedFecha = fecha.ToString("yyyy-MM-dd");

                using (var cliente = new HttpClient())
                {
                    var payload = new
                    {
                        query = query,
                        variables = new { fecha = formattedFecha }
                    };

                    var contenido = new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json");
                    var respuesta = await cliente.PostAsync(BaseEndpoint, contenido);
                    respuesta.EnsureSuccessStatusCode();

                    var contenidoRespuesta = await respuesta.Content.ReadAsStringAsync();
                    var resultado = System.Text.Json.JsonSerializer.Deserialize<JsonElement>(contenidoRespuesta);

                    var cotizacionesJson = resultado.GetProperty("data").GetProperty("obtenerCotizacion").ToString();
                    return System.Text.Json.JsonSerializer.Deserialize<List<Cotizacion>>(cotizacionesJson);
                }
            } 
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return new List<Cotizacion>();
            }
        }

        public async Task<string> RegistrarCotizacion(Cotizacion cotizacion)
        {
            try
            {
                string mutation = @"
                mutation ($cotizacion: CotizacionInput!) {
                    registrarCotizacion(cotizacion: $cotizacion) {
                        mensaje
                    }
                }";

                using (var cliente = new HttpClient())
                {
                    var payload = new
                    {
                        query = mutation,
                        variables = new { cotizacion }
                    };

                    var contenido = new StringContent(JsonConvert.SerializeObject(payload), Encoding.UTF8, "application/json");
                    var respuesta = await cliente.PostAsync(BaseEndpoint, contenido);
                    respuesta.EnsureSuccessStatusCode();

                    var contenidoRespuesta = await respuesta.Content.ReadAsStringAsync();
                    var resultado = System.Text.Json.JsonSerializer.Deserialize<JsonElement>(contenidoRespuesta);

                    return resultado.GetProperty("data").GetProperty("registrarCotizacion").GetProperty("mensaje").GetString();
                }
            }
            catch (Exception ex) 
            {
                Console.WriteLine(ex.Message);
                return "Error al agregar.";
            }
        }
    }
}
