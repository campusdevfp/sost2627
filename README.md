# 1708 · Sostenibilidad aplicada al sistema productivo

Curso práctico del módulo común **1708** (30 h) para el ciclo de **Desarrollo de Aplicaciones Web**.
Seis unidades. Cinco se trabajan con **ejercicios en Java y tests** que los corrigen; la UT3, con lectura y actividades autocorregibles.

- **Documentación:** carpeta `docs/`, publicable con MkDocs.
- **Ejercicios:** carpeta `proyecto/`, un único proyecto Maven con Java 21 y JUnit 5 como única dependencia.

## Empezar

```bash
cd proyecto
mvn test                      # todos en rojo al principio
mvn test -Dtest=Ut1AsgTest    # solo la unidad 1
```

## Ver la documentación

```bash
python3 -m venv .venv && source .venv/bin/activate
pip install -r requirements.txt
mkdocs serve                  # http://127.0.0.1:8000
```

## Publicar en GitHub Pages

1. Sube el repositorio con la rama principal `main`.
2. **Settings → Pages → Source: GitHub Actions**.
3. Ajusta `site_url` en `mkdocs.yml`.

Cada `git push` a `main` reconstruye y publica el sitio.
