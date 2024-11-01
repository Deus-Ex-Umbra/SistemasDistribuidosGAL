using System;
using System.Threading.Tasks;
using System.Net.Http;
using System.Text;
using System.Collections.Generic;
using System.Text.Json;
using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace AppCotizacion
{
    internal class ServicioREST
    {
        private const string BaseEndpoint = "http://127.0.0.1:8000/api/cotizacion";

        public async Task<List<Cotizacion>> ObtenerCotizacion(DateTime fecha)
        {
            try
            {
                string formattedFecha = fecha.ToString("yyyy-MM-dd");

                using (var cliente = new HttpClient())
                {
                    var respuesta = await cliente.GetAsync($"{BaseEndpoint}?fecha={formattedFecha}");
                    respuesta.EnsureSuccessStatusCode();

                    var contenido = await respuesta.Content.ReadAsStringAsync();
                    return System.Text.Json.JsonSerializer.Deserialize<List<Cotizacion>>(contenido);
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
                using (var cliente = new HttpClient())
                {
                    var contenido = new StringContent(System.Text.Json.JsonSerializer.Serialize(cotizacion), Encoding.UTF8, "application/json");
                    var respuesta = await cliente.PostAsync(BaseEndpoint, contenido);
                    respuesta.EnsureSuccessStatusCode();

                    return await respuesta.Content.ReadAsStringAsync();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return "Error al agregar.";
            }
        }
    }
}
