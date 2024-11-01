using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppCotizacion
{
    internal class CotizacionController
    {
        private ServicioSOAP servicioSOAP;
        private ServicioREST servicioREST;
        private ServicioGraphQL servicioGraphQL;
        public async Task<List<Cotizacion>> ObtenerCotizacionAsync(DateTime _fecha, int _tipo_servicio)
        {
            List<Cotizacion> cotizaciones = new List<Cotizacion>();
            switch (_tipo_servicio)
            {
                case 1:
                    servicioSOAP = new ServicioSOAP();
                    cotizaciones = await servicioSOAP.obtenerCotizacion(_fecha);
                    break;
                case 2:
                    servicioREST = new ServicioREST();
                    cotizaciones = await servicioREST.ObtenerCotizacion(_fecha);
                    break;
                case 3:
                    servicioGraphQL = new ServicioGraphQL();
                    cotizaciones = await servicioGraphQL.ObtenerCotizacion(_fecha);
                    break;
            }
            return cotizaciones;
        }

        public async Task<string> RegistrarCotizacionAsync(Cotizacion _cotizacion, int _tipo_servicio)
        {
            string mensaje = "";
            switch (_tipo_servicio)
            {
                case 1:
                    servicioSOAP = new ServicioSOAP();
                    mensaje = await servicioSOAP.registrarCotizacion(_cotizacion);
                    break;
                case 2:
                    servicioREST = new ServicioREST();
                    mensaje = await servicioREST.RegistrarCotizacion(_cotizacion);
                    break;
                case 3:
                    servicioGraphQL = new ServicioGraphQL();
                    mensaje = await servicioGraphQL.RegistrarCotizacion(_cotizacion);
                    break;
            }
            return mensaje;
        }
    }
}
