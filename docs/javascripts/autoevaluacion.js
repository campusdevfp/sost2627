// Preguntas autocorregibles: <div class="quiz" data-correcta="b" data-grupo="...">
(function () {
  function actualizar(grupo) {
    if (!grupo) return;
    var qs = Array.prototype.slice.call(document.querySelectorAll('.quiz[data-grupo="' + grupo + '"]'));
    var resp = qs.filter(function (q) { return q.classList.contains("respondida"); });
    var ok = resp.filter(function (q) { return q.dataset.acierto === "1"; });
    var texto = "Respondidas " + resp.length + " de " + qs.length + " · Aciertos " + ok.length;
    if (resp.length === qs.length && qs.length > 0) {
      texto += " · Nota " + (ok.length / qs.length * 10).toFixed(1).replace(".", ",");
    }
    document.querySelectorAll('.quiz-marcador[data-grupo="' + grupo + '"]').forEach(function (m) {
      m.textContent = texto;
    });
  }

  function reiniciar(grupo) {
    document.querySelectorAll('.quiz[data-grupo="' + grupo + '"]').forEach(function (q) {
      q.classList.remove("respondida");
      delete q.dataset.acierto;
      q.querySelectorAll(".quiz-op").forEach(function (o) {
        o.disabled = false;
        o.classList.remove("bien", "mal");
      });
    });
    actualizar(grupo);
  }

  document.addEventListener("DOMContentLoaded", function () {
    var grupos = {};
    document.querySelectorAll(".quiz").forEach(function (q) {
      var correcta = q.dataset.correcta;
      if (q.dataset.grupo) grupos[q.dataset.grupo] = true;
      q.querySelectorAll(".quiz-op").forEach(function (b) {
        b.addEventListener("click", function () {
          if (q.classList.contains("respondida")) return;
          var acierto = b.dataset.op === correcta;
          q.classList.add("respondida");
          q.dataset.acierto = acierto ? "1" : "0";
          q.querySelectorAll(".quiz-op").forEach(function (o) {
            o.disabled = true;
            if (o.dataset.op === correcta) o.classList.add("bien");
          });
          if (!acierto) b.classList.add("mal");
          var v = q.querySelector(".quiz-veredicto");
          if (v) v.textContent = acierto ? "✅ ¡Correcto! " : "❌ No es esa. ";
          actualizar(q.dataset.grupo);
        });
      });
    });
    document.querySelectorAll(".quiz-reiniciar").forEach(function (btn) {
      btn.addEventListener("click", function () { reiniciar(btn.dataset.grupo); });
    });
    Object.keys(grupos).forEach(actualizar);

    // Autodiagnóstico de hábitos: cuenta las casillas marcadas
    document.querySelectorAll(".habitos").forEach(function (lista) {
      var salida = lista.querySelector(".habitos-total");
      var casillas = lista.querySelectorAll("input[type=checkbox]");
      function contar() {
        var n = 0;
        casillas.forEach(function (c) { if (c.checked) n++; });
        var msg = n + " de " + casillas.length + " hábitos sostenibles. ";
        if (n >= 7) msg += "🌳 ¡Muy bien! Eres un ejemplo.";
        else if (n >= 4) msg += "🌱 Vas por buen camino: elige un hábito nuevo para esta semana.";
        else msg += "🌰 Hay margen de mejora: empieza por uno solo, el más fácil.";
        salida.textContent = msg;
      }
      casillas.forEach(function (c) { c.addEventListener("change", contar); });
      contar();
    });
  });
})();
