using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Threading.Tasks;

namespace AppCotizacion
{
    public partial class FormCotizacion : Form
    {
        private CotizacionController cotizacionController;
        private System.Windows.Forms.GroupBox groupBoxObtener;
        private System.Windows.Forms.DateTimePicker dateTimePickerObtener;
        private System.Windows.Forms.DataGridView dataGridViewObtener;
        private System.Windows.Forms.ComboBox comboBoxObtener;
        private System.Windows.Forms.Button buttonObtener;

        private System.Windows.Forms.GroupBox groupBoxRegistrar;
        private System.Windows.Forms.DateTimePicker dateTimePickerRegistrar;
        private System.Windows.Forms.NumericUpDown numericUpDownRegistrar1;
        private System.Windows.Forms.NumericUpDown numericUpDownRegistrar2;
        private System.Windows.Forms.ComboBox comboBoxRegistrar;
        private System.Windows.Forms.TextBox textBoxMensaje;
        private System.Windows.Forms.Button buttonRegistrar;

        public FormCotizacion()
        {
            cotizacionController = new CotizacionController();
            InitializeComponent();
        }

        private async Task ObtenerCotizacion()
        {
            if (comboBoxObtener.SelectedIndex >= 0)
            {
                int tipoServicio = comboBoxObtener.SelectedIndex + 1;
                DateTime fecha = dateTimePickerObtener.Value;

                List<Cotizacion> cotizaciones = await cotizacionController.ObtenerCotizacionAsync(fecha, tipoServicio);
                dataGridViewObtener.DataSource = cotizaciones;
            }
            else
            {
                MessageBox.Show("Seleccione un tipo de servicio para obtener la cotización.");
            }
        }

        private async Task RegistrarCotizacion()
        {
            if (comboBoxRegistrar.SelectedIndex >= 0)
            {
                int tipoServicio = comboBoxRegistrar.SelectedIndex + 1;
                Cotizacion cotizacion = new Cotizacion
                {
                    fecha = dateTimePickerRegistrar.Value,
                    cotizacion = (double)numericUpDownRegistrar1.Value,
                    cotizacion_oficial = (double)numericUpDownRegistrar2.Value
                };

                string mensaje = await cotizacionController.RegistrarCotizacionAsync(cotizacion, tipoServicio);
                textBoxMensaje.Text = mensaje;
            }
            else
            {
                MessageBox.Show("Seleccione un tipo de servicio para registrar la cotización.");
            }
        }

        private void InitializeComponent()
        {
            this.groupBoxObtener = new System.Windows.Forms.GroupBox();
            this.dateTimePickerObtener = new System.Windows.Forms.DateTimePicker();
            this.dataGridViewObtener = new System.Windows.Forms.DataGridView();
            this.comboBoxObtener = new System.Windows.Forms.ComboBox();
            this.buttonObtener = new System.Windows.Forms.Button();

            this.groupBoxRegistrar = new System.Windows.Forms.GroupBox();
            this.dateTimePickerRegistrar = new System.Windows.Forms.DateTimePicker();
            this.numericUpDownRegistrar1 = new System.Windows.Forms.NumericUpDown();
            this.numericUpDownRegistrar2 = new System.Windows.Forms.NumericUpDown();
            this.comboBoxRegistrar = new System.Windows.Forms.ComboBox();
            this.textBoxMensaje = new System.Windows.Forms.TextBox();
            this.buttonRegistrar = new System.Windows.Forms.Button();

            this.SuspendLayout();

            // groupBoxObtener
            this.groupBoxObtener.Controls.Add(this.dateTimePickerObtener);
            this.groupBoxObtener.Controls.Add(this.comboBoxObtener);
            this.groupBoxObtener.Controls.Add(this.buttonObtener);
            this.groupBoxObtener.Controls.Add(this.dataGridViewObtener);
            this.groupBoxObtener.Location = new System.Drawing.Point(10, 10);
            this.groupBoxObtener.Name = "groupBoxObtener";
            this.groupBoxObtener.Size = new System.Drawing.Size(375, 400);
            this.groupBoxObtener.Text = "Obtener";

            // dateTimePickerObtener
            this.dateTimePickerObtener.Location = new System.Drawing.Point(15, 30);
            this.dateTimePickerObtener.Name = "dateTimePickerObtener";
            this.dateTimePickerObtener.Size = new System.Drawing.Size(340, 22);

            // comboBoxObtener
            this.comboBoxObtener.Items.AddRange(new string[] { "SOAP", "REST", "GraphQL" });
            this.comboBoxObtener.Location = new System.Drawing.Point(15, 70);
            this.comboBoxObtener.Name = "comboBoxObtener";
            this.comboBoxObtener.Size = new System.Drawing.Size(340, 24);

            // buttonObtener
            this.buttonObtener.Location = new System.Drawing.Point(15, 110);
            this.buttonObtener.Name = "buttonObtener";
            this.buttonObtener.Size = new System.Drawing.Size(340, 30);
            this.buttonObtener.Text = "Accionar";
            this.buttonObtener.UseVisualStyleBackColor = true;
            this.buttonObtener.Click += async (sender, e) => await ObtenerCotizacion();

            // dataGridViewObtener
            this.dataGridViewObtener.Location = new System.Drawing.Point(15, 150);
            this.dataGridViewObtener.Name = "dataGridViewObtener";
            this.dataGridViewObtener.Size = new System.Drawing.Size(340, 220);
            this.dataGridViewObtener.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;

            // groupBoxRegistrar
            this.groupBoxRegistrar.Controls.Add(this.dateTimePickerRegistrar);
            this.groupBoxRegistrar.Controls.Add(this.numericUpDownRegistrar1);
            this.groupBoxRegistrar.Controls.Add(this.numericUpDownRegistrar2);
            this.groupBoxRegistrar.Controls.Add(this.comboBoxRegistrar);
            this.groupBoxRegistrar.Controls.Add(this.buttonRegistrar);
            this.groupBoxRegistrar.Controls.Add(this.textBoxMensaje);
            this.groupBoxRegistrar.Location = new System.Drawing.Point(410, 10);
            this.groupBoxRegistrar.Name = "groupBoxRegistrar";
            this.groupBoxRegistrar.Size = new System.Drawing.Size(375, 400);
            this.groupBoxRegistrar.Text = "Registrar";

            // dateTimePickerRegistrar
            this.dateTimePickerRegistrar.Location = new System.Drawing.Point(15, 30);
            this.dateTimePickerRegistrar.Name = "dateTimePickerRegistrar";
            this.dateTimePickerRegistrar.Size = new System.Drawing.Size(340, 22);

            // numericUpDownRegistrar1
            this.numericUpDownRegistrar1.DecimalPlaces = 2;
            this.numericUpDownRegistrar1.Location = new System.Drawing.Point(15, 70);
            this.numericUpDownRegistrar1.Name = "numericUpDownRegistrar1";
            this.numericUpDownRegistrar1.Size = new System.Drawing.Size(340, 22);

            // numericUpDownRegistrar2
            this.numericUpDownRegistrar2.DecimalPlaces = 2;
            this.numericUpDownRegistrar2.Location = new System.Drawing.Point(15, 110);
            this.numericUpDownRegistrar2.Name = "numericUpDownRegistrar2";
            this.numericUpDownRegistrar2.Size = new System.Drawing.Size(340, 22);

            // comboBoxRegistrar
            this.comboBoxRegistrar.Items.AddRange(new string[] { "SOAP", "REST", "GraphQL" });
            this.comboBoxRegistrar.Location = new System.Drawing.Point(15, 150);
            this.comboBoxRegistrar.Name = "comboBoxRegistrar";
            this.comboBoxRegistrar.Size = new System.Drawing.Size(340, 24);

            // buttonRegistrar
            this.buttonRegistrar.Location = new System.Drawing.Point(15, 190);
            this.buttonRegistrar.Name = "buttonRegistrar";
            this.buttonRegistrar.Size = new System.Drawing.Size(340, 30);
            this.buttonRegistrar.Text = "Accionar";
            this.buttonRegistrar.UseVisualStyleBackColor = true;
            this.buttonRegistrar.Click += async (sender, e) => await RegistrarCotizacion();

            // textBoxMensaje
            this.textBoxMensaje.Location = new System.Drawing.Point(15, 230);
            this.textBoxMensaje.Multiline = true;
            this.textBoxMensaje.Name = "textBoxMensaje";
            this.textBoxMensaje.Size = new System.Drawing.Size(340, 140);
            this.textBoxMensaje.Text = "Mensaje";

            // FormCotizacion
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.groupBoxObtener);
            this.Controls.Add(this.groupBoxRegistrar);
            this.Name = "FormCotizacion";
            this.Text = "Cotizaciones";
            this.ResumeLayout(false);

            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewObtener)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownRegistrar1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownRegistrar2)).EndInit();
        }
    }
}
