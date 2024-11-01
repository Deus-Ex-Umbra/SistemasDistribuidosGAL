using System;

namespace AppCotizacion
{
    internal class Cotizacion
    {
        public int id { get; set; }
        public DateTime fecha { get; set; }
        public double cotizacion { get; set; }
        public double cotizacion_oficial { get; set; }
    }
}
