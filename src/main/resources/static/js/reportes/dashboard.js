document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById("graficoBarras").getContext("2d");

    const data = {
        labels: JSON.parse(document.getElementById("graficoBarras").getAttribute("data-labels")),
        datasets: [{
            label: 'Movimientos por tipo',
            data: JSON.parse(document.getElementById("graficoBarras").getAttribute("data-values")),
            borderWidth: 1
        }]
    };

    new Chart(ctx, {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: { display: false }
            },
            scales: {
                y: { beginAtZero: true }
            }
        }
    });
});
