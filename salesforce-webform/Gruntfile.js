/* eslint strict: [2, "global"] */
'use strict';

module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    meta: {
      version: '1.0.0'
    },
    pkg: grunt.file.readJSON('package.json'),
    banner: '/**\n' +
              '* Project:   <%= pkg.name %>\n' +
              '* Version:   <%= pkg.version %>\n' +
              '* Date:      <%= grunt.template.today("yyyy-mm-dd") %>\n' +
              '* Copyright: Solnet\n' +
              '*/',

    // Task configuration.
    clean: {
      src: ['src/main/resources/public/js', 'src/main/resources/public/css', 'src/main/resources/public/fonts']
    },
    copy: {
      angular: {
        src: ['bower_components/angular/angular.min.js'],
        dest: 'src/main/resources/public/js/angular.min.js'
      },
      jquery: {
        src: ['bower_components/jquery/dist/jquery.min.js'],
        dest: 'src/main/resources/public/js/jquery.min.js'
      },    
      bootstrapJs: {
        src: 'bower_components/bootstrap/dist/js/bootstrap.min.js',
        dest: 'src/main/resources/public/js/bootstrap.min.js'
      },
      bootstrapCss: {
        src: 'bower_components/bootstrap/dist/css/bootstrap.min.css',
        dest: 'src/main/resources/public/css/bootstrap.min.css'
      },
      bootstrapTheme: {
        src: 'bower_components/bootstrap/dist/css/bootstrap-theme.min.css',
        dest: 'src/main/resources/public/css/bootstrap-theme.min.css'
      },
      bootstrapGlyphicons: {
        expand: true,
        cwd: 'bower_components/bootstrap/fonts/',
        src: ['**'],
        dest: 'src/main/resources/public/fonts/'
      },        
      bootstrapValidatorCss: {
        src: 'bower_components/bootstrapValidator/dist/css/bootstrapValidator.min.css',
        dest: 'src/main/resources/public/css/bootstrapValidator.min.css'
      },
      bootstrapValidatorJs: {
        src: 'bower_components/bootstrapValidator/dist/js/bootstrapValidator.min.js',
        dest: 'src/main/resources/public/js/bootstrapValidator.min.js'
      },
      solnetCss: {
        src: 'src/main/resources/templates/app.css',
        dest: 'src/main/resources/public/css/solnet.css'
      }    
    },
    eslint: {
      target: [
        'Gruntfile.js',
        'src/main/resources/public/js/**/*.js'
      ]
    },
    uglify: {
      options: {
        banner: '<%= banner %>'
      },
      dist: {
        src: 'src/main/resources/templates/app.js',
        dest: 'src/main/resources/public/js/app.min.js'
      }
    },
  });

  // filter npm modules and load them
  require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

  // Add task aliases
  grunt.registerTask('default', ['clean', 'copy', 'uglify', 'eslint']);
};
