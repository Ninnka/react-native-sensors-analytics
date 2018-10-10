using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Sensors.Analytics.RNSensorsAnalytics
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNSensorsAnalyticsModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNSensorsAnalyticsModule"/>.
        /// </summary>
        internal RNSensorsAnalyticsModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNSensorsAnalytics";
            }
        }
    }
}
