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
      src: 'src/main/resources/public/js'
    },
    copy: {
      angular: {
        src: 'bower_components/angular/angular.min.js',
        dest: 'src/main/resources/public/js/angular.min.js',
      },
    },
    eslint: {
      target: [
        'Gruntfile.js',
        'src/main/resources/public/js/**/*.js'
      ]
    }
  });

  // filter npm modules and load them
  require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

  // Add task aliases
  grunt.registerTask('default', ['clean', 'copy', 'eslint']);
};
