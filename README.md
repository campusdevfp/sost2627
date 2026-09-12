# 1708 · Sostenibilidad aplicada al sistema productivo — repositorio del alumnado

Materiales, actividades y proyectos del módulo **1708 (30 h)** para el ciclo **DAW**, trabajado con **Java 21 y Spring Boot**.

Sitio publicado: `https://USUARIO.github.io/repo-alumno-1708/` *(cambia la URL cuando lo publiques)*

## Contenido

```
docs/
├── index.md, el-curso.md          portada y guía del curso
├── ud1 … ud6/index.md             las seis unidades (una por RA)
├── recursos/                      entorno, rigor y fuentes, Java y Spring sostenibles
├── proyectos/ud1 … ud6/           seis proyectos Spring Boot con tests
└── retos/                         retos de programación
```

## Ver el sitio en local mientras editas

```bash
python3 -m venv .venv && source .venv/bin/activate    # Windows: .venv\Scripts\activate
pip install -r requirements.txt
mkdocs serve
```

Abre `http://127.0.0.1:8000`. Cada vez que guardes un `.md`, la página se recarga sola.

## Publicar en GitHub Pages

Hay dos formas; elige una.

### A) Automática con GitHub Actions (recomendada)

El repositorio incluye `.github/workflows/publicar.yml`. Configuración, una sola vez:

1. Sube el repositorio a GitHub con la rama principal llamada `main`.
2. En el repositorio: **Settings → Pages → Build and deployment → Source: GitHub Actions**.
3. Ajusta `site_url` en `mkdocs.yml` con tu usuario y el nombre del repositorio.

A partir de ahí, cada `git push` a `main` reconstruye y publica el sitio en uno o dos minutos. El progreso se ve en la pestaña **Actions**.

```bash
git add . && git commit -m "Corrijo la UD3" && git push
```

### B) Manual con `mkdocs gh-deploy`

Un solo comando desde tu equipo: construye el sitio y lo empuja a la rama `gh-pages`.

```bash
mkdocs gh-deploy
```

Requiere poner **Settings → Pages → Source: Deploy from a branch → `gh-pages` / `(root)`**. Es lo más rápido para un cambio suelto, pero publica lo que tengas en tu carpeta, esté o no confirmado en git.

### Notas

- Si el repositorio es **privado**, GitHub Pages solo está disponible en los planes de pago. Con un repositorio **público**, el sitio también lo es: no subas ahí soluciones ni exámenes.
- `mkdocs build --strict` convierte los avisos en errores, así que la publicación falla si hay un enlace roto. Es deliberado: mejor enterarse antes de publicar.
- Para repartir el sitio sin servidor (pendrive o carpeta compartida): `OFFLINE=true mkdocs build` y comprime la carpeta `site/`.

## Los proyectos

Cada carpeta `docs/proyectos/udN/` es un proyecto Spring Boot independiente con su Maven Wrapper:

```bash
cd docs/proyectos/ud1
./mvnw test              # Windows: mvnw.cmd test
./mvnw spring-boot:run
```

Requiere **JDK 21** o superior. No hace falta instalar Maven.
