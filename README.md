# 📱 Orçamento e Vendas (Android)

Aplicativo Android para cálculo de peso de peças metálicas, simulação de valor por kg, histórico de orçamentos e exportação em PDF.

## ✅ Stack atual

- **Kotlin 1.9.24**
- **Android Gradle Plugin 8.4.1**
- **Jetpack Compose + Material 3**
- **MVVM com ViewModel + StateFlow**
- **Hilt** (injeção de dependência)
- **Room** (persistência local)
- **Coroutines + Flow**
- **Navigation Compose**
- **iText 7** (geração de PDF)

## 🎯 Funcionalidades implementadas

- Cálculo de peso para múltiplos tipos de peça
- Cálculo de peso total por quantidade
- Cálculo de valor total com preço/kg
- Seleção de material (Aço, Inox, Alumínio)
- Histórico local de orçamentos
- Exportação/compartilhamento de orçamento em PDF
- Validações de entrada no fluxo de cálculo

## 🧱 Tipos de peça suportados

- Chapa
- Tubo Quadrado
- Tubo Retangular
- Viga U
- Viga U Enrijecida
- Tubo Redondo

## 📂 Estrutura atual do projeto

### Raiz

```text
.
├── app/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── gradlew.bat
```

### Módulo `app`

```text
app/
├── build.gradle.kts
├── proguard-rules.pro
└── src/
    ├── main/
    │   ├── AndroidManifest.xml
    │   ├── java/com/orcamentoevendas/
    │   │   ├── App.kt
    │   │   ├── MainActivity.kt
    │   │   ├── data/
    │   │   │   ├── local/
    │   │   │   │   ├── dao/
    │   │   │   │   ├── database/
    │   │   │   │   ├── entity/
    │   │   │   │   └── relation/
    │   │   │   └── repository/
    │   │   ├── di/
    │   │   ├── domain/
    │   │   │   └── model/
    │   │   ├── model/
    │   │   ├── ui/
    │   │   │   ├── components/
    │   │   │   ├── navigation/
    │   │   │   ├── screens/
    │   │   │   ├── state/
    │   │   │   ├── theme/
    │   │   │   └── viewmodel/
    │   │   └── utils/
    │   └── res/
    │       ├── drawable/
    │       ├── mipmap-*/
    │       ├── values/
    │       └── xml/
    ├── test/
    │   └── java/com/orcamentoevendas/
    │       ├── domain/
    │       ├── ui/viewmodel/
    │       └── testutils/
    └── androidTest/
        └── java/com/example/oramenteevendas/
```

## 🧠 Papel de cada camada

- **data/local**: banco Room (`dao`, `entity`, `database`) e relações.
- **data/repository**: acesso aos dados para o restante do app.
- **di**: módulos de injeção com Hilt.
- **domain**: regras de negócio e cálculo (ex.: `CalculadoraPeso`).
- **ui**: telas Compose, componentes, estado e ViewModels.
- **utils**: utilitários como formatadores e exportação de PDF.

## ▶️ Como executar o projeto

### Pré-requisitos

- **Android Studio Hedgehog ou superior**
- **JDK 17**
- Android SDK instalado (compileSdk/targetSdk **34**)
- Emulador Android ou dispositivo físico (minSdk **24**)

### 1) Clonar o repositório
## 🧪 Testes Automatizados

Atualmente o projeto possui uma base inicial de testes cobrindo regras essenciais:

- `CalculadoraPesoTest`: valida fórmulas e casos de borda da camada de domínio
- `CalculadoraViewModelTest`: valida cálculo por material, mensagens de erro e persistência
- `MainDispatcherRule`: suporte para testes de corrotinas com `viewModelScope`

Esses testes reduzem regressões e complementam a validação prática no dispositivo Android.

---

## ➡️ Próximo Passo Sugerido

Com material, validações, testes iniciais e navegação via `NavHost` implementados, o próximo passo recomendado é **fortalecer testes instrumentados e fluxo de navegação**:

1. Adicionar testes instrumentados para navegação entre `Calculadora` e `Histórico`
2. Validar cenários de erro na UI (mensagem de validação visível em tela)
3. Cobrir fluxo de salvar e compartilhar orçamento no histórico

Isso aumenta a confiabilidade ponta a ponta do app em cenários reais de uso.
Com material, validações e testes iniciais implementados, o próximo passo recomendado é a **migração da navegação para `NavHost` com rotas tipadas**:

1. Definir destinos tipados para `Calculadora` e `Histórico`
2. Centralizar argumentos/rotas em um único arquivo de navegação
3. Cobrir fluxo de navegação com testes instrumentados básicos

Isso reduz erros de rota, melhora manutenção e prepara o app para novas telas.

---

```bash
git clone <URL_DO_REPOSITORIO>
cd OrcamentoLojaAndroid
```

### 2) Abrir no Android Studio

1. Abra o Android Studio
2. Clique em **Open** e selecione a pasta do projeto
3. Aguarde o **Gradle Sync** terminar

### 3) Executar o app

1. Selecione o device (emulador ou celular via USB)
2. Clique em **Run 'app'**

### 4) Executar via terminal (opcional)

> No Linux/macOS:

```bash
chmod +x gradlew
./gradlew :app:installDebug
```

> No Windows (PowerShell/CMD):

```bash
gradlew.bat :app:installDebug
```

## 🧪 Testes

Testes unitários disponíveis em `app/src/test` (incluindo domínio e ViewModel):

```bash
./gradlew :app:testDebugUnitTest
```
- [x] Implementar ViewModel (MVVM incremental)
- [x] Persistência de histórico com Room
- [x] Melhorar UI com Material 3 e cards de resultado
- [x] Compartilhar orçamento em PDF
- [x] Implementar seleção de material (aço, inox, alumínio)
- [x] Melhorar validações e mensagens de erro de entrada
- [x] Migrar navegação para `NavHost` (rotas tipadas)
- [ ] Migrar navegação para `NavHost` (rotas tipadas)
- [x] Expandir testes unitários de cálculo e ViewModel

Testes instrumentados (device/emulador conectado):

```bash
./gradlew :app:connectedDebugAndroidTest
```

## 🛠️ Build útil no dia a dia

```bash
./gradlew :app:assembleDebug
```


## 📸 Screenshots

<p align="center">
  <img src="app/screenshots/viga_u.jpg" width="250" alt="Tela de cálculo para Viga U"/>
  <img src="app/screenshots/tubo_retangular.jpg" width="250" alt="Tela de cálculo para Tubo Retangular"/>
  <img src="app/screenshots/tubo_redondo.jpg" width="250" alt="Tela de cálculo para Tubo Redondo"/>
</p>

## 👨‍💻 Autor

Desenvolvido por **João Manoel**.
