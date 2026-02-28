pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Bloqueia repositórios definidos nos subprojetos
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    // Repositórios padrão para dependências
    repositories {
        google()
        mavenCentral()
    }
}

// Nome do projeto principal
rootProject.name = "OrcamentoEVendas"

// Módulos do projeto
