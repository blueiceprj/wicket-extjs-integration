# Introduction #

The wicket-extjs-integration project is developed under the Apache License.  Because Ext JS is released under the terms of the GPL, the licensing is non-trivial.

## Licenses ##
The licenses that are at work are
  * The [Apache Software License version 2.0](http://www.apache.org/licenses/LICENSE-2.0) (ASL2)
  * The [GNU General Public License version 3.0](http://www.gnu.org/licenses/gpl-3.0.html) (GPL3)

## Sencha Licensing Exceptions ##
Sencha has created exceptions to the GPL allow development of suitably alternatively licensed libraries and applications.  Two licensing exceptions for Ext JS exist:
  * [Exception for Development](http://www.sencha.com/legal/open-source-faq/open-source-license-exception-for-development/)
  * [Exception for Applications](http://www.sencha.com/legal/open-source-faq/open-source-license-exception-for-applications/)


# Modules #
The different modules in the project interact with Ext JS in different ways, so they each are dealt with in a separate manner.

## wicket-extjs module License ##

The (java) Wicket integration is developed under the "Exception for Development".  It does not (cannot) bundle the Ext JS code.  Since this module contains the java code, it will be required when compiling a Wicket application.

## wicket-extjs-bundle module License ##

This module contains the Ext JS code and it's modifications.  It is therefore released under the same terms Ext JS is.  I.e. this module is licensed under version 3 of the GNU General Public License (GPLv3).

It is possible to use this bundle in an free/open source application with the use of the "Exception for Applications".  It is also possible to develop against this library with the use of the "Exception for Development. This is also what the wicket-extjs module is using.

## wicket-extjs-examples License ##

This application includes Ext JS code (via an wicket-extjs-bundle dependency) and uses the "Exception for Applications" to be an ASL