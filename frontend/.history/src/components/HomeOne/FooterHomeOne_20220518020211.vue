<template>
  <header class="header" id="header">
    <nav class="nav container">
      <a href="#" class="nav__logo">Marlon</a>
      <div class="nav__menu" id="nav-menu">
        <ul class="nav__list">
          <li class="nav__item">
            <a href="#home" class="nav__link active-link">
              <i class="bx bx-home-alt nav__icon"></i>
              <span class="nav__name">Home</span>
            </a>
          </li>
          <li class="nav__item">
            <a href="#about" class="nav__link">
              <i class="bx bx-user nav__icon"></i>
              <span class="nav__name">About</span>
            </a>
          </li>
          <li class="nav__item">
            <a href="#skills" class="nav__link">
              <i class="bx bx-book-alt nav__icon"></i>
              <span class="nav__name">Skills</span>
            </a>
          </li>
          <li class="nav__item">
            <a href="#portfolio" class="nav__link">
              <i class="bx bx-briefcase-alt nav__icon"></i>
              <span class="nav__name">Portfolio</span>
            </a>
          </li>
          <li class="nav__item">
            <a href="#contactme" class="nav__link">
              <i class="bx bx-message-square-detail nav__icon"></i>
              <span class="nav__name">Contactme</span>
            </a>
          </li>
        </ul>
      </div>
      <img src="assets/img/perfil.png" alt="" class="nav__img" />
    </nav>
  </header>
</template>

<script>
export default {};
</script>

<style>
@media screen and (max-width: 320px) {
  .nav__name {
    display: none;
  }
}
@media screen and (min-width: 576px) {
  .nav__list {
    justify-content: center;
    column-gap: 3rem;
  }
}
@media screen and (min-width: 767px) {
  .nav {
    height: 4.5rem;
  }
  .nav__img {
    display: none;
  }
  .nav__icon {
    display: none;
  }
  .nav__name {
    font-size: 0.938rem;
  }
  .nav__link:hover {
    color: hsl(174, 63%, 40%);
  }
}
</style>
