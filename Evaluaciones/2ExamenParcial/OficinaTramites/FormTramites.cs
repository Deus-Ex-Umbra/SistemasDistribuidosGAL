using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net.Http;
using System.Security.Policy;
using System.Text;

namespace OficinaTramites
{
    public partial class FormTramites : Form
    {
        public FormTramites()
        {
            InitializeComponent();
        }

        private void FormTramites_Load(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private async void button1_Click(object sender, EventArgs e)
        {
           
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            string id = textBox3.Text;
            string ci = textBox4.Text;
            string nombre = textBox5.Text;
            string titulo = textBox6.Text;
            string fecha = textBox7.Text;
            string endpoint = "http://127.0.0.1:8000/api/titulos";
            HttpClient cliente = new HttpClient();
        }
    }
}
