document.addEventListener("DOMContentLoaded", function () {
  const canvas = document.getElementById("graficoMovimientos");
  if (!canvas) return;

  const labels = JSON.parse(canvas.dataset.labels || "[]");
  const values = JSON.parse(canvas.dataset.values || "[]");

  new Chart(canvas, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: 'Movimientos',
        data: values,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        borderColor: 'rgba(255, 99, 132, 1)',
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true,
          ticks: { precision: 0 }
        }
      }
    }
  });
});
