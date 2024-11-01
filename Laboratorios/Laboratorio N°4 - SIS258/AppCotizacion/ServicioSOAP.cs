using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace AppCotizacion
{
    internal class ServicioSOAP
    {
        private string endpoint = "http://127.0.0.1:50586/pronostico.asmx";

        public async Task<List<Cotizacion>> obtenerCotizacion(DateTime _fecha)
        {
           try
            {
                endpoint += "?op=obtenerCotizaciones";
                string fecha = _fecha.ToString("yyyy-MM-dd");
                string soap = $@"POST /pronostico.asmx HTTP/1.1
                            Host: localhost
                            Content-Type: text/xml; charset=utf-8
                            Content-Length: length
                            SOAPAction: ""http://tempuri.org/obtenerCotizaciones""

                            <?xml version=""1.0"" encoding=""utf-8""?>
                            <soap:Envelope xmlns:xsi=""http://www.w3.org/2001/XMLSchema-instance"" xmlns:xsd=""http://www.w3.org/2001/XMLSchema"" xmlns:soap=""http://schemas.xmlsoap.org/soap/envelope/"">
                              <soap:Body>
                                <obtenerCotizaciones xmlns=""http://tempuri.org/"">
                                  <fecha>{fecha}</fecha>
                               </obtenerCotizaciones>
                              </soap:Body>
                            </soap:Envelope>";
                using (var cliente = new HttpClient())
                {
                    var contenido = new StringContent(soap, Encoding.UTF8, "text/xml");
                    contenido.Headers.Add("SOAPAction", "http://tempuri.org/obtenerCotizaciones");
                    var respuesta = await cliente.PostAsync(endpoint, contenido);
                    var contenidoRespuesta = await respuesta.Content.ReadAsStringAsync();
                    return deserializar(contenidoRespuesta);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return new List<Cotizacion>();
            }
        }

        private List<Cotizacion> deserializar(string xml)
        {
            XmlSerializer serializador = new XmlSerializer(typeof(List<Cotizacion>));
            return (List<Cotizacion>)serializador.Deserialize(new System.IO.StringReader(xml));
        }

        public async Task<string> registrarCotizacion(Cotizacion _cotizacion)
        {
            try
            {
                endpoint += "?op=registrarCotizacion";
                string soap = $@"POST /pronostico.asmx HTTP/1.1
                            Host: localhost
                            Content-Type: text/xml; charset=utf-8
                            Content-Length: length
                            SOAPAction: ""http://tempuri.org/registrarCotizacion""

                            <?xml version=""1.0"" encoding=""utf-8""?>
                            <soap:Envelope xmlns:xsi=""http://www.w3.org/2001/XMLSchema-instance"" xmlns:xsd=""http://www.w3.org/2001/XMLSchema"" xmlns:soap=""http://schemas.xmlsoap.org/soap/envelope/"">
                              <soap:Body>
                                <registrarCotizacion xmlns=""http://tempuri.org/"">
                                  <cotizacion>
                                    <fecha>{_cotizacion.fecha}</fecha>
                                    <cotizacion>{_cotizacion.cotizacion}</cotizacion>
                                    <cotizacion_oficial>{_cotizacion.cotizacion_oficial}</cotizacion_oficial>
                                  </cotizacion>
                                </registrarCotizacion.
                              </soap:Body>
                            </soap:Envelope>";
                using (var cliente = new HttpClient())
                {
                    var contenido = new StringContent(soap, Encoding.UTF8, "text/xml");
                    contenido.Headers.Add("SOAPAction", "http://tempuri.org/registrarCotizacion");
                    var respuesta = await cliente.PostAsync(endpoint, contenido);
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
