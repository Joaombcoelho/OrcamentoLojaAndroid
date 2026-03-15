# 📱 Calculadora de Peso de Materiais Metálicos

Aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose** para cálculo de peso e valor de peças metálicas utilizadas na indústria metalúrgica.

O projeto foi criado com base em necessidades reais de cálculo no dia a dia de oficina/serralheria, permitindo calcular peso unitário, peso por metro e valor total de diferentes perfis metálicos.

---

## 🚀 Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- ViewModel + StateFlow
- Hilt (injeção de dependências)
- Room (persistência local)
- Coroutines + Flow
- Exportação de PDF

---

## 🎯 Funcionalidades

O aplicativo já oferece:

- ✅ Cálculo de peso por tipo de peça
- ✅ Kg por metro
- ✅ Peso total por quantidade
- ✅ Cálculo de valor por Kg (opcional)
- ✅ Salvar orçamento no histórico
- ✅ Visualizar histórico de orçamentos
- ✅ Compartilhar orçamento em PDF

---

## 🧱 Tipos de Peças Suportadas

- Chapa
- Tubo Quadrado
- Tubo Retangular
- Viga U
- Viga U Enrijecida
- Tubo Redondo

---

## 🧮 Estrutura de Cálculo

As regras matemáticas ficam isoladas na camada **domain**, através do objeto:

```kotlin
CalculadoraPeso
```

Ele contém funções específicas para cada tipo de peça:

- `calcularChapa()`
- `calcularTuboQuadrado()`
- `calcularTuboRetangular()`
- `calcularVigaU()`
- `calcularVigaUEnrijecida()`
- `calcularTuboRedondo()`

---

## 📂 Estrutura do Projeto

```text
com.orcamentoevendas
│
├── data/
│   ├── local/           # Room (DAO, entities, database)
│   └── repository/      # Repositórios da aplicação
│
├── di/                  # Módulos Hilt
│
├── domain/              # Regras de cálculo e modelos de domínio
│
├── ui/
│   ├── components/      # Componentes reutilizáveis
│   ├── screens/         # Telas Compose
│   ├── state/           # UiState
│   └── viewmodel/       # ViewModels
│
└── MainActivity.kt
```

- **data** → Persistência local e acesso a dados
- **di** → Configuração de injeção de dependências
- **domain** → Regras de negócio e cálculos
- **ui** → Interface, estado de tela e fluxo de interação

---

## 📌 Conversões Importantes

- Conversão de milímetros para metros: `mm / 1000.0`
- Quantidade padrão = `1` se valor inválido
- Densidade padrão atual: Aço

---

## 🛠️ Roadmap

- [x] Implementar ViewModel (MVVM incremental)
- [x] Persistência de histórico com Room
- [x] Melhorar UI com Material 3 e cards de resultado
- [x] Compartilhar orçamento em PDF
- [ ] Implementar seleção de material (aço, inox, alumínio)
- [ ] Melhorar validações e mensagens de erro de entrada
- [ ] Migrar navegação para `NavHost` (rotas tipadas)
- [ ] Expandir testes unitários de cálculo e ViewModel

---

## 🧠 Objetivo do Projeto

Este projeto faz parte da minha evolução como desenvolvedor Android, aplicando:

- Modelagem de domínio
- Separação de responsabilidades
- Organização arquitetural progressiva
- Boas práticas de commits
- Evolução incremental com foco em qualidade

---

## 📸 Screenshots

<p align="center">
  <img src="app/screenshots/viga_u.jpg" width="250" alt="Tela de cálculo para Viga U"/>
  <img src="app/screenshots/tubo_retangular.jpg" width="250" alt="Tela de cálculo para Tubo Retangular"/>
  <img src="app/screenshots/tubo_redondo.jpg" width="250" alt="Tela de cálculo para Tubo Redondo"/>
</p>

---

## 👨‍💻 Autor

Desenvolvido por João Manoel.
